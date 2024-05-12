package io.github.lubosgarancovsky.domain.shared.listing;

import java.util.Optional;

public interface ListingAttribute {

    String apiName();

    boolean isForSorting();

    boolean isForFiltering();

    default Optional<Integer> minSize() {
        return Optional.empty();
    }

    default Boolean isTranslated() {
        return false;
    }
}
