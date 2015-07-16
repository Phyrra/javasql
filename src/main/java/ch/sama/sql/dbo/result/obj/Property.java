package ch.sama.sql.dbo.result.obj;

import java.lang.reflect.Field;

class Property {
    private Field field;
    private Column column;
    private String name;

    Property(Field field) {
        this.field = field;
        this.column = field.getAnnotation(Column.class);
        this.name = column.name();

        field.setAccessible(true);
    }

    boolean is(String colName) {
        return colName.equalsIgnoreCase(name);
    }

    void set(Object parent, Object value) throws IllegalAccessException {
        field.set(parent, value);
    }

    String getName() {
        return name;
    }

    Object getValue(Object parent) throws IllegalAccessException {
        return field.get(parent);
    }
}
