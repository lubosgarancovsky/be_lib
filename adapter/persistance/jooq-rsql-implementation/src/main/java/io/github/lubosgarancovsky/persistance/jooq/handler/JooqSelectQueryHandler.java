package io.github.lubosgarancovsky.persistance.jooq.handler;

public interface JooqSelectQueryHandler<T, R> {
    R handle(T var1);
}
