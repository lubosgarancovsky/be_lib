package io.github.lubosgarancovsky.persistance.jooq.handler.mapper;

import io.github.lubosgarancovsky.domain.listing.ListingQuery;
import io.github.lubosgarancovsky.domain.listing.PaginatedResult;
import org.jooq.Record;
import org.jooq.SelectQuery;

import java.util.function.Function;
import java.util.stream.Stream;

public interface PaginatedResultMapper<LISTING extends ListingQuery, RESULT extends PaginatedResult> {
    
    RESULT map(TriFunction<Stream<Record>, Integer, LISTING, RESULT> var1);

    <RECORDS> RESULT map(Function<SelectQuery, RECORDS> var1, TriFunction<RECORDS, Integer, LISTING, RESULT> var2);

}
