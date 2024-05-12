package io.github.lubosgarancovsky.core.listing;

import org.immutables.value.Value;

@Value.Immutable
public interface ListOrdering<T extends ListingAttribute> {

    @Value.Parameter
    T attribute();

    @Value.Parameter
    boolean ascending();
}
