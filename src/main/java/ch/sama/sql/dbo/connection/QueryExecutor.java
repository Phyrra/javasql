package ch.sama.sql.dbo.connection;

import java.util.List;
import java.util.Map;

public interface QueryExecutor {
    public void execute(String query);

    public List<Map<String, Object>> query(String query);

    public Map<String, Object> get(String query);
}
