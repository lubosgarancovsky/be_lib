package io.github.lubosgarancovsky.domain.listing;

import org.immutables.value.Value;

import java.util.Objects;

@Value.Immutable
@Value.Style(builderVisibility = Value.Style.BuilderVisibility.PACKAGE)
public interface PageSize {

    @Value.Parameter
    Integer value();

    static PageSize of(Integer maxPageSize, Integer inputPageSize) {
        Objects.requireNonNull(maxPageSize, "MaxPageSize must be not null");
        if (inputPageSize == null) {
            return ImmutablePageSize.of(maxPageSize);
        }
        return ImmutablePageSize.of(Math.min(maxPageSize, inputPageSize));
    }
}
