package io.github.lubosgarancovsky.persistance.jooq.metadata;

import io.github.lubosgarancovsky.persistance.converter.FieldValueConverter;
import org.jooq.Field;

public class JooqColumnInfo<T> {
    
    private Field<T> field;

    private FieldValueConverter<T> converter;

    public JooqColumnInfo(){
    }

    public Field<T> getField() { return this.field; }

    public void setField(Field<T> field) { this.field = field; }

    public FieldValueConverter<T> getConverter() { return this.converter; }

    public void setConverter(FieldValueConverter<T> converter) { this.converter = converter; }
    
}
