package io.github.lubosgarancovsky.core.rsql.jooq;

import io.github.lubosgarancovsky.core.listing.ListingQuery;
import io.github.lubosgarancovsky.core.listing.PaginatedResult;
import io.github.lubosgarancovsky.core.shared.TriFunction;
import org.jooq.SelectQuery;

import java.util.function.Function;
import java.util.stream.Stream;

public interface PaginatedResultMapper<
        LISTING extends ListingQuery, RESULT extends PaginatedResult> {

    RESULT map(TriFunction<Stream<Record>, Integer, LISTING, RESULT> fn);

    <RECORDS> RESULT map(
            Function<SelectQuery, RECORDS> records, TriFunction<RECORDS, Integer, LISTING, RESULT> fn);
}
