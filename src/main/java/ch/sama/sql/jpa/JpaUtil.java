package ch.sama.sql.jpa;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JpaUtil {
    public JpaUtil() { }

    public List<Field> getColumns(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();

        List<Field> columns = new ArrayList<Field>();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                field.setAccessible(true);

                columns.add(field);
            }
        }

        return columns;
    }

    public boolean isColumn(String colName, Field field) {
        return field
                .getAnnotation(Column.class)
                .name()
                .equalsIgnoreCase(colName);
    }

    public<T> T toObject(Map<String, Object> map, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T instance = clazz.newInstance();

        List<Field> columns = getColumns(clazz);

        for (String key : map.keySet()) {
            Object val = map.get(key);

            if (val != null) {
                for (Field col : columns) {
                    if (isColumn(key, col)) {
                        col.set(instance, val);
                    }
                }
            }
        }

        return instance;
    }

    public<T> Map<String, Object> toMap(T obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<String, Object>();

        List<Field> columns = getColumns(obj.getClass());

        for (Field col : columns) {
            map.put(
                    col.getAnnotation(Column.class).name(),
                    col.get(obj)
            );
        }

        return map;
    }
}