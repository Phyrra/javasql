package ch.sama.sql.tsql.dialect;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;

public class TSqlField extends Field {
    public TSqlField(String field) {
        super(field);
    }

    public TSqlField(Table table, String field) {
        super(table, field);
    }

    public TSqlField(String table, String field) {
        super(table, field);
    }

    @Override
    public String getString() {
        StringBuilder builder = new StringBuilder();

        Table table = getTable();
        String tableName = getTableName();

        if (table != null) {
            builder.append(table.getString());
            builder.append(".");
        } else if (tableName != null) {
            builder.append("[");
            builder.append(tableName);
            builder.append("].");
        }

        builder.append("[");
        builder.append(getName());
        builder.append("]");

        return builder.toString();
    }
}
