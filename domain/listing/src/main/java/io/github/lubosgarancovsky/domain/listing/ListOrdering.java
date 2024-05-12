package io.github.lubosgarancovsky.domain.listing;

import io.github.lubosgarancovsky.domain.shared.listing.ListingAttribute;
import org.immutables.value.Value;

@Value.Immutable
public interface ListOrdering<T extends ListingAttribute> {

    @Value.Parameter
    T attribute();

    @Value.Parameter
    boolean ascending();
}
