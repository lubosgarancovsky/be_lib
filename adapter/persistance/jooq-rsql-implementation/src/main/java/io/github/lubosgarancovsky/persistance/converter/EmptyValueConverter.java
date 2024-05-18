package io.github.lubosgarancovsky.persistance.converter;

public class EmptyValueConverter implements FieldValueConverter<String> {
    
    public EmptyValueConverter() {
        
    }

    public boolean isAccessibleFor(Class clazz) {
        return true;
    }

    public String from(String from) { return from; }
}
