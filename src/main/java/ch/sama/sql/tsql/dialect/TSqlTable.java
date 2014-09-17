package ch.sama.sql.tsql.dialect;

import ch.sama.sql.dbo.Table;

public class TSqlTable extends Table {
    public TSqlTable(String table) {
        super(table);
    }

    public TSqlTable(String schema, String table) {
        super(schema, table);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        String schema = getSchema();
        if (schema != null) {
            builder.append("[");
            builder.append(schema);
            builder.append("].");
        }

        builder.append("[");
        builder.append(getName());
        builder.append("]");

        String alias = getAlias();
        if (alias != null) {
            builder.append(" AS [");
            builder.append(alias);
            builder.append("]");
        }

        return builder.toString();
    }
}
