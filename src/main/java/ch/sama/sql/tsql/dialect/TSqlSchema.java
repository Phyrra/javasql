package ch.sama.sql.tsql.dialect;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Schema;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.dbo.connection.QueryExecutor;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.exception.ObjectNotFoundException;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Value;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TSqlSchema implements Schema {
    private static final TSqlQueryFactory factory = new TSqlQueryFactory();

    private Map<String, Table> tables;

    public TSqlSchema(QueryExecutor executor) {
        tables = new HashMap<String, Table>();

        List<Map<String, Object>> list = executor.query(
                factory.create()
                        .select(
                                factory.field("TABLE_SCHEMA"),
                                factory.field("TABLE_NAME"),
                                factory.query(
                                        factory.create()
                                                .select(factory.field("COLUMN_NAME"))
                                                .from(factory.table("INFORMATION_SCHEMA", "TABLE_CONSTRAINTS").as("tc"))
                                                .join(factory.table("INFORMATION_SCHEMA", "CONSTRAINT_COLUMN_USAGE").as("ccu")).on(Condition.eq(factory.field("tc", "CONSTRAINT_NAME"), factory.field("ccu", "CONSTRAINT_NAME")))
                                                .where(
                                                        Condition.and(
                                                                Condition.eq(factory.field("tc", "CONSTRAINT_TYPE"), factory.string("PRIMARY KEY")),
                                                                Condition.eq(factory.field("tc", "TABLE_NAME"), factory.field("TABLES", "TABLE_NAME"))
                                                        )
                                                )
                                ).as("PRIMARY_KEY")
                        )
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
                            .select(
                                    factory.field("COLUMN_NAME"),
                                    factory.field("DATA_TYPE"),
                                    factory.field("CHARACTER_MAXIMUM_LENGTH"),
                                    factory.field("IS_NULLABLE"),
                                    factory.field("COLUMN_DEFAULT")
                            )
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

                Object defaultValue = column.get("COLUMN_DEFAULT");
                if (defaultValue != null) {
                    // The data type is inconsequential here
                    f.setDefault(factory.plain(defaultValue.toString()));
                }

                t.addColumn(f);
            }

            t.setPrimaryKey(row.get("PRIMARY_KEY").toString());
        }
    }

    @Override
    public List<Table> getTables() {
        return new ArrayList<Table>(tables.values());
    }

    @Override
    public Table getTable(String name) {
        if (!tables.containsKey(name)) {
            throw new ObjectNotFoundException("Table " + name + " could not be cound");
        }

        return tables.get(name);
    }

    public String getTableSchema(Table table) {
        // TODO: Ignores FK constraints

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

            Value defaultValue = field.getDefault();
            if (defaultValue != null) {
                String defaultString = defaultValue.toString();

                if (!defaultString.startsWith("(") && defaultString.endsWith(")")) {
                    defaultString = "(" + defaultString + ")";
                }

                builder.append(" CONSTRAINT [DF_");
                builder.append(table.getName());
                builder.append("_");
                builder.append(field.getName());
                builder.append("] DEFAULT ");

                if (!defaultString.startsWith("(") && defaultString.endsWith(")")) {
                    builder.append("(");
                    builder.append(defaultString);
                    builder.append(")");
                } else {
                    builder.append(defaultString);
                }
            }

            prefix = ",\n";
        }

        builder.append(",\n\tCONSTRAINT [PK_");
        builder.append(table.getName());
        builder.append("] PRIMARY KEY CLUSTERED (\n\t\t[");
        builder.append(table.getPrimaryKey().getName());
        builder.append("] ASC\n\t)\n) ON [PRIMARY]");
        // Ignores all the options: WITH  (...) ON [PRIMARY]

        return builder.toString();
    }
}
