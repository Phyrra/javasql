package ch.sama.sql.dbo.result;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class MapResult {
    private Map<String, Object> map;
    
    MapResult() {
        map = new LinkedHashMap<String, Object>();
    }

    public Object get(String key) {
        return map.get(key);
    }

    public Object put(String key, Object value) {
        return map.put(key, value);
    }

    public Set<String> keySet() {
        return map.keySet();
    }

    public boolean contains(String key) {
        return map.containsKey(key);
    }

    public<T> T getAs(String key, Class<T> type) {
        Object o = get(key);

        if (o == null) {
            return null;
        }

        if (type.isAssignableFrom(o.getClass())) {
            return type.cast(o);
        }

        throw new ClassCastException(getErrorMessage(key, o.getClass().getName(), type.getName()));
    }

    private String getErrorMessage(String key, String actual, String expected) {
        return "{" + actual + "} cannot be cast to {" + expected + "}";
    }

    public String getAsString(String key) {
        return getAs(key, String.class);
    }

    public Integer getAsInt(String key) {
        return getAs(key, Integer.class);
    }

    public Long getAsLong(String key) {
        return getAs(key, Long.class);
    }

    public Short getAsShort(String key) {
        return getAs(key, Short.class);
    }

    public Double getAsDouble(String key) {
        return getAs(key, Double.class);
    }

    public Float getAsFloat(String key) {
        return getAs(key, Float.class);
    }

    public Date getAsDate(String key) {
        return getAs(key, Date.class);
    }
}
