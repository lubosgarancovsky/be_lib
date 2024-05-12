package io.github.lubosgarancovsky.restapi.dto.listing;

import java.util.List;

public interface ListingResponse<T> {

    List<T> items();

    Integer totalCount();

    Integer page();

    Integer pageSize();
}
