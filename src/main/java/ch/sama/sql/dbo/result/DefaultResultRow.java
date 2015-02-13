package ch.sama.sql.dbo.result;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class DefaultResultRow extends ResultRow {
    Map<String, Object> map;
    
    public DefaultResultRow() {
        map = new LinkedHashMap<String, Object>();
    }
    
    @Override
    public Object get(String key) {
        return map.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return map.put(key, value);
    }
    
    @Override
    public Set<String> keySet() {
        return map.keySet();
    }
    
    @Override
    public boolean contains(String key) {
        return map.containsKey(key);
    }
}
