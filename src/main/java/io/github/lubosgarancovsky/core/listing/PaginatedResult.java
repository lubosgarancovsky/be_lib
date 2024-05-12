package io.github.lubosgarancovsky.core.listing;

import java.util.List;

public interface PaginatedResult<T extends PaginatedResultItem> {

    List<T> items();

    Integer page();

    Integer pageSize();

    Integer totalCount();
}
