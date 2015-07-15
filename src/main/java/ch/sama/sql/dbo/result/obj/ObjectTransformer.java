package ch.sama.sql.dbo.result.obj;

import ch.sama.sql.dbo.result.IResultSetTransformer;
import ch.sama.sql.dbo.result.TransformerHelper;
import ch.sama.sql.dbo.result.obj.Column;
import ch.sama.sql.dbo.result.obj.Entity;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObjectTransformer<T> implements IResultSetTransformer<List<T>> {
    private static class Tupel {
        private Field field;
        private Column column;
        private String name;

        public Tupel(Field field) {
            this.field = field;
            this.column = field.getAnnotation(Column.class);
            this.name = column.name();

            field.setAccessible(true);
        }

        public boolean is(String colName) {
            return colName.equalsIgnoreCase(name);
        }

        public void set(Object parent, Object value) throws IllegalAccessException {
            field.set(parent, value);
        }
    }

    private Class<T> clazz;

    public ObjectTransformer(Class<T> clazz) {
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> transform(ResultSet resultSet) throws SQLException {
        if (!clazz.isAnnotationPresent(Entity.class)) {
            throw new SQLException("Object must be annotated with @Entity");
        }

        List<T> result = new ArrayList<T>();

        String[] colNames = TransformerHelper.getColumnNames(resultSet);
        List<Tupel> columns = getColumns();

        while (resultSet.next()) {
            T instance;

            try {
                instance = (T) clazz.newInstance();

                for (int i = 0; i < colNames.length; ++i) {
                    String colName = colNames[i];
                    Object val = resultSet.getObject(i + 1);

                    if (val != null) {
                        for (Tupel column : columns) {
                            if (column.is(colName)) {
                                column.set(instance, TransformerHelper.defaultTransform(val));
                            }
                        }
                    }
                }
            } catch (Exception e) {
                throw new SQLException(e);
            }

            result.add(instance);
        }

        return result;
    }

    private List<Tupel> getColumns() {
        Field[] fields = clazz.getDeclaredFields();

        List<Tupel> columns = new ArrayList<Tupel>();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                columns.add(new Tupel(field));
            }
        }

        return columns;
    }
}