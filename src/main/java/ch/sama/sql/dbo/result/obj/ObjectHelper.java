package ch.sama.sql.dbo.result.obj;

import ch.sama.sql.jpa.Column;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectHelper {
    static <T> List<Property> getProperties(Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();

        List<Property> props = new ArrayList<Property>();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                props.add(new Property(field));
            }
        }

        return props;
    }

    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T instance = clazz.newInstance();

        List<Property> props = getProperties(clazz);

        for (String key : map.keySet()) {
            Object val = map.get(key);

            if (val != null) {
                for (Property prop : props) {
                    if (prop.is(key)) {
                        prop.set(instance, val);
                    }
                }
            }
        }

        return instance;
    }

    public static <T> Map<String, Object> objectToMap(T obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<String, Object>();

        List<Property> props = getProperties(obj.getClass());

        for (Property prop : props) {
            map.put(prop.getName(), prop.getValue(obj));
        }

        return map;
    }
}
