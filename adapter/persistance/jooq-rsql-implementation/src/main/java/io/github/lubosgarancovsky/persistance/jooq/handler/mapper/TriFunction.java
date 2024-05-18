package io.github.lubosgarancovsky.persistance.jooq.handler.mapper;

@FunctionalInterface
public interface TriFunction<A, B, C, R> {
    R apply( A var1, B var2, C var3);
}
