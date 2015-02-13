package ch.sama.sql.dbo.result;

import java.util.Date;
import java.util.Set;

public abstract class ResultRow {
    public abstract Object get(String key);
    public abstract Object put(String key, Object value);
    public abstract Set<String> keySet();
    public abstract boolean contains(String key);
    
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
