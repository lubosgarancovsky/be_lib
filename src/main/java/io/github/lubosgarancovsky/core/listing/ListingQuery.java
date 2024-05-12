package io.github.lubosgarancovsky.core.listing;

import cz.jirutka.rsql.parser.ast.Node;
import org.immutables.value.Value;

import java.util.List;
import java.util.Optional;

public interface ListingQuery<T extends ListingAttribute> {

    @Value.Default
    default Integer page() {
        return 1;
    }

    Optional<Node> rsqlQuery();

    List<ListOrdering<T>> orderings();

    PageSize pageSize();

//    @Value.Default
//    default LanguageCode[] languageCodes() {
//        return new LanguageCode[] {};
//    }

    @Value.Derived
    default int limit() {
        return pageSize().value();
    }

    @Value.Derived
    default int offset() {
        return Math.max(page() - 1, 0) * pageSize().value();
    }
}
