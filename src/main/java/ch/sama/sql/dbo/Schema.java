package ch.sama.sql.dbo;

import ch.sama.sql.dbo.connection.QueryExecutor;
import ch.sama.sql.query.base.QueryFactory;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schema {
    private Map<String, Table> tables;

    public Schema(QueryExecutor executor, QueryFactory factory) {
        tables = new HashMap<String, Table>();

        List<Map<String, Object>> list = executor.query(
                factory.create()
                        .select(factory.field("TABLE_SCHEMA"), factory.field("TABLE_NAME"))
                        .from(factory.table("INFORMATION_SCHEMA", "TABLES"))
                .toString()
        );

        for (Map<String, Object> row : list) {
            String schema = row.get("TABLE_SCHEMA").toString();
            String table = row.get("TABLE_NAME").toString();

            tables.put(table, factory.table(schema, table));
        }
    }

    public Map<String, Table> getTables() {
        return tables;
    }

    public Table getTable(String name) {
        return tables.get(name);
    }
}
