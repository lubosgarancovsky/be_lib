package io.github.lubosgarancovsky.restapi.listing;

import cz.jirutka.rsql.parser.ast.*;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ListFilteringVisitor extends NoArgRSQLVisitorAdapter<Set<String>> {

    private final Set<String> allowedAttributes;

    private ListFilteringVisitor(Set<String> allowedAttributes) {
        this.allowedAttributes = allowedAttributes;
    }

    static ListFilteringVisitor of(Set<String> allowed) {
        return new ListFilteringVisitor(allowed);
    }

    static ListFilteringVisitor of(String... allowed) {
        final Set<String> set = Stream.of(allowed).collect(Collectors.toSet());
        return new ListFilteringVisitor(set);
    }

    @Override
    public Set<String> visit(AndNode node) {
        return visitChildren(node);
    }

    @Override
    public Set<String> visit(OrNode node) {
        return visitChildren(node);
    }

    @Override
    public Set<String> visit(ComparisonNode node) {
        final String selector = node.getSelector();
        return allowedAttributes.contains(selector)
                ? Collections.emptySet()
                : Collections.singleton(selector);
    }

    private Set<String> visitChildren(LogicalNode node) {
        return node.getChildren().stream()
                .flatMap(child -> child.accept(this).stream())
                .collect(Collectors.toSet());
    }
}