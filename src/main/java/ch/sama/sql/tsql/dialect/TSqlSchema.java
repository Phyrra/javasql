package ch.sama.sql.tsql.dialect;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Schema;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.dbo.connection.QueryExecutor;
import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.query.helper.Condition;

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

            Table t = factory.table(schema, table);
            tables.put(table, t);

            List<Map<String, Object>> columns = executor.query(
                    factory.create()
                            .select(factory.field("COLUMN_NAME"), factory.field("DATA_TYPE"), factory.field("CHARACTER_MAXIMUM_LENGTH"), factory.field("IS_NULLABLE"))
                            .from(factory.table("INFORMATION_SCHEMA", "COLUMNS"))
                            .where(Condition.eq(factory.field("TABLE_NAME"), factory.string(table)))
                    .toString()
            );

            for (Map<String, Object> column : columns) {
                Field f = new Field(t, column.get("COLUMN_NAME").toString());

                String dataType = column.get("DATA_TYPE").toString();
                Object maxLength = column.get("CHARACTER_MAXIMUM_LENGTH");
                if (maxLength != null) {
                    dataType += "(" + maxLength.toString() + ")";
                }
                f.setDataType(dataType);

                String nullable = column.get("IS_NULLABLE").toString();
                if ("NO".equals(nullable)) {
                    f.setNullable(false);
                } else {
                    f.setNullable(true);
                }

                t.addColumn(f);
            }
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

    public String getTableSchema(Table table) {
        // TODO: Private Key Info is missing
        // TODO: Ignores constraints and defaults

        StringBuilder builder = new StringBuilder();
        String prefix = "";

        builder.append("CREATE TABLE ");
        builder.append(table.toString());
        builder.append("(\n");

        for (Field field : table.getColumns()) {
            builder.append(prefix);

            builder.append("\t[");
            builder.append(field.getName());
            builder.append("] [");

            String dataType = field.getDataType();
            if (dataType.contains("(")) {
                int idx = dataType.indexOf("(");

                builder.append(dataType.substring(0, idx));
                builder.append("]");
                builder.append(dataType.substring(idx));
            } else {
                builder.append(dataType);
                builder.append("]");
            }

            if (field.getNullable()) {
                builder.append(" NULL");
            } else {
                builder.append(" NOT NULL");
            }

            prefix = ",\n";
        }

        builder.append("\n)");

        return builder.toString();
    }
}
