package io.github.lubosgarancovsky.persistance.converter;

public interface FieldValueConverter<T> {
    boolean isAccessibleFor(Class var1);

    T from(String var1);
}
