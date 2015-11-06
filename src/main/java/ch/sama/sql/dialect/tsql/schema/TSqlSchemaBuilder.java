package ch.sama.sql.dialect.tsql.schema;

import ch.sama.sql.dbo.Table;

import java.util.HashMap;
import java.util.Map;

public class TSqlSchemaBuilder {
    private Map<String, Table> tables;

    public TSqlSchemaBuilder() {
        tables = new HashMap<String, Table>();
    }

    public TSqlSchemaBuilder addTable(Table table) {
        tables.put(table.getName(), table);

        return this;
    }

    public TSqlSchema getSchema() {
        return new TSqlSchema(tables);
    }
}
