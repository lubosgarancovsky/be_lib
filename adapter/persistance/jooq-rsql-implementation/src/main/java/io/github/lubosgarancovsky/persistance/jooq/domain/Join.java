package io.github.lubosgarancovsky.persistance.jooq.domain;

import org.immutables.value.Value;
import org.jooq.Condition;
import org.jooq.JoinType;
import org.jooq.Table;

@Value.Immutable
public interface Join {
    
    Table table();

    JoinType type();

    Condition condition();
}
