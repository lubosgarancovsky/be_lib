package io.github.lubosgarancovsky.persistance.jooq.builder;

import io.github.lubosgarancovsky.persistance.jooq.metadata.JooqRsqlMetadataAware;
import io.github.lubosgarancovsky.persistance.rsql.RsqlNodeBuilder;

public interface JooqRsqlListingMetadataAwareBuilder<T> extends RsqlNodeBuilder<T>, JooqRsqlMetadataAware {
    
}
