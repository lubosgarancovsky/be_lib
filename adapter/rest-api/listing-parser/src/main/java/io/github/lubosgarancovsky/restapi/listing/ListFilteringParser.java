package io.github.lubosgarancovsky.restapi.listing;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.RSQLParserException;
import cz.jirutka.rsql.parser.ast.*;
import io.github.lubosgarancovsky.domain.listing.ListFiltering;
import io.github.lubosgarancovsky.domain.listing.ListFilteringOperators;
import io.github.lubosgarancovsky.domain.shared.listing.ListingAttribute;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ListFilteringParser {

    private final RSQLParser rsqlParser;

    public ListFilteringParser() {
        final Set<ComparisonOperator> operators = RSQLOperators.defaultOperators();
        operators.add(ListFilteringOperators.LIKE);
        this.rsqlParser = new RSQLParser(operators);
    }

    public <T extends ListingAttribute> ListFiltering<T> parse(String source, List<T> attributes) {
        if(source == null || source.isEmpty()) {
            return ListFiltering.none();
        }

        try {
            final Node rsqlNode = rsqlParser.parse(source);

            final List<T> supportedAttributes =
                    attributes.stream().filter(ListingAttribute::isForFiltering).toList();

            final List<String> namesOfSupportedAttributes =
                    supportedAttributes.stream().map(ListingAttribute::apiName).collect(Collectors.toList());

            final Map<String, Integer> attributesWithMinSize =
                    supportedAttributes.stream()
                            .filter(a -> a.minSize().isPresent())
                            .collect(Collectors.toMap(ListingAttribute::apiName, a -> a.minSize().get()));

            checkAttributeMinSize(rsqlNode, attributesWithMinSize);

            final ListFilteringVisitor visitor =
                    ListFilteringVisitor.of(new HashSet<>(namesOfSupportedAttributes));
            final List<String> namesOfUnsupportedAttributes = new ArrayList<>(rsqlNode.accept(visitor));

            if (!namesOfUnsupportedAttributes.isEmpty()) {
                throw ListParserErrorCode.UNSUPPORTED_FILTERING_ATTR
                        .createError(
                                String.join(",", namesOfUnsupportedAttributes),
                                String.join(",", namesOfSupportedAttributes))
                        .convertToException();
            }
            return ListFiltering.some(rsqlNode);

        } catch(RSQLParserException e) {
            throw ListParserErrorCode.INVALID_FILTERING_QUERY.createError().convertToException();
        }
    }

    private void checkAttributeMinSize(LogicalNode node, Map<String, Integer> attributesWithMinSize) {
        node.getChildren().forEach(child -> checkAttributeMinSize(child, attributesWithMinSize));
    }

    private void checkAttributeMinSize(Node node, Map<String, Integer> attributesWithMinSize) {
        if (node instanceof LogicalNode logicalNode) {
            checkAttributeMinSize(logicalNode, attributesWithMinSize);
        } else if (node instanceof ComparisonNode comparisonNode) {
            checkAttributeMinSize(comparisonNode, attributesWithMinSize);
        }
    }

    private void checkAttributeMinSize(
            ComparisonNode node, Map<String, Integer> attributesWithMinSize) {
        final String selector = node.getSelector();
        if (attributesWithMinSize.containsKey(selector)
                && isSizeLessThanMin(node, attributesWithMinSize)) {
            throw ListParserErrorCode.FILTERING_ATTR_REQUIRES_MIN_SIZE
                    .createError(selector, attributesWithMinSize.get(selector))
                    .convertToException();
        }
    }

    private boolean isSizeLessThanMin(
            ComparisonNode node, Map<String, Integer> attributesWithMinSize) {
        return node.getArguments().stream()
                .anyMatch(a -> a.length() < attributesWithMinSize.get(node.getSelector()));
    }
}
