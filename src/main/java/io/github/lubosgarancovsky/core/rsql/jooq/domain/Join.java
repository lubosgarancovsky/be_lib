package io.github.lubosgarancovsky.core.rsql.jooq.domain;

import org.immutables.value.Value;
import org.jooq.Condition;
import org.jooq.Table;

@Value.Immutable
public interface Join {

    Table table();


    Condition condition();
}
