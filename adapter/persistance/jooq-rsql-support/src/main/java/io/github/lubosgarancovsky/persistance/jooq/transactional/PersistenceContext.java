package io.github.lubosgarancovsky.persistance.jooq.transactional;

import org.jooq.DSLContext;

public interface PersistenceContext {
    DSLContext get();
}
