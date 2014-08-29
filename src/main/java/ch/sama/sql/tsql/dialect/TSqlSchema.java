package ch.sama.sql.tsql.dialect;

import ch.sama.sql.dbo.Schema;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.dbo.connection.QueryExecutor;
import ch.sama.sql.query.base.QueryFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TSqlSchema implements Schema {
    private Map<String, Table> tables;

    public TSqlSchema(QueryExecutor executor, QueryFactory factory) {
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

    @Override
    public Map<String, Table> getTables() {
        return tables;
    }

    @Override
    public Table getTable(String name) {
        return tables.get(name);
    }
}
