package io.github.lubosgarancovsky.core.listing;


public enum NoListingAttributes implements ListingAttribute {
    ;

    @Override
    public String apiName() {
        throw new IllegalStateException();
    }

    @Override
    public boolean isForSorting() {
        throw new IllegalStateException();
    }

    @Override
    public boolean isForFiltering() {
        throw new IllegalStateException();
    }
}
