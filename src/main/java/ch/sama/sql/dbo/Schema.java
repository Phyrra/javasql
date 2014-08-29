package ch.sama.sql.dbo;

import java.util.Map;

public interface Schema {
    public Map<String, Table> getTables();

    public Table getTable(String name);
}
