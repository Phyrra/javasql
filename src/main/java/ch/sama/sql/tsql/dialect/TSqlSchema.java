package ch.sama.sql.tsql.dialect;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.ISchema;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.dbo.connection.IQueryExecutor;
import ch.sama.sql.dbo.generator.ITableFilter;
import ch.sama.sql.dbo.result.MapResult;
import ch.sama.sql.query.base.IQueryFactory;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.exception.ObjectNotFoundException;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.tsql.type.TYPE;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TSqlSchema implements ISchema {
    private static final IQueryFactory fac = new TSqlQueryFactory();

    private Map<String, Table> tables;

    public TSqlSchema(IQueryExecutor<MapResult> executor) {
        loadSchema(executor, table -> true);
    }

    public TSqlSchema(IQueryExecutor<MapResult> executor, ITableFilter filter) {
        loadSchema(executor, filter);
    }

    private void loadSchema(IQueryExecutor<MapResult> executor, ITableFilter filter) {
        TSqlFunctionFactory fnc = new TSqlFunctionFactory();

        tables = new HashMap<String, Table>();

        List<MapResult> result = executor.query(
                fac.query()
                        .select(
                                fac.value().field("TABLE_SCHEMA"),
                                fac.value().field("TABLE_NAME")
                        )
                        .from(fac.source().table("INFORMATION_SCHEMA", "TABLES"))
                .getSql()
        );

        for (MapResult row : result) {
            String schema = row.getAsString("TABLE_SCHEMA");
            String table = row.getAsString("TABLE_NAME");

            if (!filter.filter(table)) {
                continue;
            }

            System.out.println("Creating table: " + table);

            Table t = new Table(schema, table);
            tables.put(table, t);

            List<MapResult> columns = executor.query(
                    fac.query()
                            .select(
                                    fac.value().field("COLUMN_NAME"),
                                    fac.value().field("DATA_TYPE"),
                                    fac.value().field("CHARACTER_MAXIMUM_LENGTH"),
                                    fac.value().field("IS_NULLABLE"),
                                    fac.value().field("COLUMN_DEFAULT"),
                                    fnc.coalesce(
                                            fac.value().query(
                                                    fac.query()
                                                            .select(fac.value().numeric(1))
                                                            .from(fac.source().table("INFORMATION_SCHEMA", "TABLE_CONSTRAINTS").as("tc"))
                                                            .join(fac.source().table("INFORMATION_SCHEMA", "CONSTRAINT_COLUMN_USAGE").as("ccu")).on(Condition.eq(fac.value().field("tc", "CONSTRAINT_NAME"), fac.value().field("ccu", "CONSTRAINT_NAME")))
                                                            .where(
                                                                    Condition.and(
                                                                            Condition.eq(fac.value().field("tc", "CONSTRAINT_TYPE"), fac.value().string("PRIMARY KEY")),
                                                                            Condition.eq(fac.value().field("tc", "TABLE_NAME"), fac.value().string(table)),
                                                                            Condition.eq(fac.value().field("ccu", "COLUMN_NAME"), fac.value().field("COLUMNS", "COLUMN_NAME"))
                                                                    )
                                                            )
                                            ),
                                            fac.value().numeric(0)
                                    ).as("IS_PKEY")
                            )
                            .from(fac.source().table("INFORMATION_SCHEMA", "COLUMNS"))
                            .where(Condition.eq(fac.value().field("TABLE_NAME"), fac.value().string(table)))
                    .getSql()
            );

            for (MapResult column : columns) {
                Field f = new Field(t, column.getAsString("COLUMN_NAME"));

                String dataType = column.getAsString("DATA_TYPE");
                Object maxLength = column.get("CHARACTER_MAXIMUM_LENGTH");
                if (maxLength != null && maxLength instanceof Integer) {
                    int l = (Integer) maxLength;

                    if (l == -1) {
                        dataType += "(max)";
                    } else {
                        dataType += "(" + l + ")";
                    }
                }
                f.setDataType(TYPE.fromString(dataType));

                String nullable = column.getAsString("IS_NULLABLE");
                if ("NO".equals(nullable)) {
                    f.setNullable(false);
                } else {
                    f.setNullable(true);
                }

                String defaultValue = column.getAsString("COLUMN_DEFAULT");
                if (defaultValue != null) {
                    // The data type is inconsequential here
                    f.setDefault(fac.value().plain(defaultValue));
                }

                t.addColumn(f);

                if (column.getAsInt("IS_PKEY") == 1) {
                    f.setAsPrimaryKey();
                }
            }
        }
    }

    public TSqlSchema(String schema) {
        parse(schema);
    }

    public TSqlSchema(File file) {
        InputStream stream;
        try {
            stream = new FileInputStream(file);
        } catch(FileNotFoundException e) {
            throw new ObjectNotFoundException(e.getMessage(), e);
        }

        load(stream);
    }

    public TSqlSchema(InputStream stream) {
        load(stream);
    }

    private void load(InputStream stream) {
        StringBuilder builder = new StringBuilder();
        String prefix = "";

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(prefix);
                builder.append(line);

                prefix = "\n";
            }

            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        parse(builder.toString());
    }

    @Override
    public List<Table> getTables() {
        return new ArrayList<Table>(tables.values());
    }

    @Override
    public boolean hasTable(String name) {
        return tables.containsKey(name);
    }

    @Override
    public Table getTable(String name) {
        if (!tables.containsKey(name)) {
            throw new ObjectNotFoundException("Table " + name + " could not be cound");
        }

        return tables.get(name);
    }

    public static String getTableSchema(Table table) {
        // TODO: Ignores FK constraints

        StringBuilder builder = new StringBuilder();
        String prefix = "";

        builder.append("CREATE TABLE ");
        builder.append(table.getString(fac.renderer()));
        builder.append("(\n");

        for (Field field : table.getColumns()) {
            builder.append(prefix);
            builder.append("\t");
            builder.append(getColumnSchema(field));

            prefix = ",\n";
        }

        if (table.hasPrimaryKey()) {
            builder.append(",\n\tCONSTRAINT [PK_");
            builder.append(table.getName());
            builder.append("] PRIMARY KEY CLUSTERED (\n");

            prefix = "";
            for (Field pKey : table.getPrimaryKey()) {
                builder.append(prefix);
                builder.append("\t\t[");
                builder.append(pKey.getName());
                builder.append("] ASC");

                prefix = ",\n";
            }

            builder.append("\n\t)\n) ON [PRIMARY]");
            // Ignores all the options: WITH  (...) ON [PRIMARY]
        } else {
            builder.append("\n)");
        }

        return builder.toString();
    }

    public static String getColumnSchema(Field field) {
        StringBuilder builder = new StringBuilder();

        builder.append("[");
        builder.append(field.getName());
        builder.append("] [");

        String dataType = field.getDataType().getString();
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
            String defaultString = defaultValue.getValue();

            if (!defaultString.startsWith("(") && defaultString.endsWith(")")) {
                defaultString = "(" + defaultString + ")";
            }

            builder.append(" CONSTRAINT [DF_");
            builder.append(field.getTableName());
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

        return builder.toString();
    }

    private void parse(String schema) {
        // This will be very basic!
        //  I have no intention of writing a complete SQL parser

        final int NONE = 0;
        final int TABLE_BLOCK = 1;
        final int PRIMARY_BLOCK = 2;

        tables = new HashMap<String, Table>();
        int currentBlock = NONE;
        Table table = null;

        Pattern pattern = Pattern.compile("\\[(\\w+)\\](\\((\\d+|[m|M][a|A][x|X])\\))?");

        String[] lines = schema.split("\n");
        for (String line : lines) {
            line = line.trim();

            if (line.startsWith("CREATE TABLE")) {
                currentBlock = TABLE_BLOCK;

                Matcher matcher = pattern.matcher(line);

                String tableSchema = null;
                String tableName = null;

                int i = 0;
                while (matcher.find()) { // [dbo].[table]
                    if (i == 0) {
                        tableSchema = matcher.group(1);
                    } else if (i == 1) {
                        tableName = matcher.group(1);
                    } else {
                        break;
                    }

                    ++i;
                }
                if (i == 0) {
                    throw new BadSqlException("Schema error: " + line);
                } else if (i == 1) {
                    tableName = tableSchema;
                    table = new Table(tableName);
                } else {
                    table = new Table(tableSchema, tableName);
                }

                tables.put(tableName, table);
            } else if (line.startsWith("CONSTRAINT")) { // the only supported constraint is PRIMARY
                currentBlock = PRIMARY_BLOCK;
            } else if (line.startsWith(")")) {
                if (currentBlock == NONE) {
                    throw new BadSqlException("Schema error, unbalanced Brackets: " + line);
                } else if (currentBlock == TABLE_BLOCK) {
                    table = null;
                    currentBlock = NONE;
                } else if (currentBlock == PRIMARY_BLOCK) {
                    if (table == null) {
                        throw new BadSqlException("Schema error, primary key block without table: " + line);
                    }

                    currentBlock = TABLE_BLOCK;
                }
            } else if (line.startsWith("--") || line.length() == 0) {
                // ignore
            } else {
                Matcher matcher = pattern.matcher(line);
                String fieldName = null;
                int i = 0;

                switch (currentBlock) {
                    case NONE:
                        throw new BadSqlException("Schema error, blockless field: " + line);
                    case TABLE_BLOCK:
                        String dataType = null;

                        while (matcher.find()) { // [field] [type] ...
                            if (i == 0) {
                                fieldName = matcher.group(1);
                            } else if (i == 1) {
                                dataType = matcher.group(1);

                                String length = matcher.group(2);
                                if (length != null) {
                                    dataType += length;
                                }
                            } else {
                                break;
                            }

                            ++i;
                        }
                        if (i == 0) {
                            throw new BadSqlException("Schema error, no field name: " + line);
                        }

                        Field field = new Field(table, fieldName);

                        if (line.contains("NOT NULL")) {
                            field.setNullable(false);
                        } else {
                            field.setNullable(true); // nullable = true is default if none is set
                        }

                        if (dataType != null) {
                            field.setDataType(TYPE.fromString(dataType));
                        }

                        if (line.contains("CONSTRAINT") && line.contains("DEFAULT")) { // The only supported constraint is DEFAULT
                            int idx0 = line.indexOf("DEFAULT");
                            int idx1 = line.indexOf("(", idx0 + 1);
                            int idx2 = line.lastIndexOf(")");

                            String defaultValue = line.substring(idx1, idx2 + 1);
                            field.setDefault(new Value(null, defaultValue));
                        }

                        table.addColumn(field); // if this does invoke NPE something went wrong

                        break;
                    case PRIMARY_BLOCK:
                        while (matcher.find()) { // [field]
                            if (i == 0) {
                                fieldName = matcher.group(1);
                            } else {
                                break;
                            }

                            ++i;
                        }
                        if (i == 0) {
                            throw new BadSqlException("Schema error, no field name: " + line);
                        }

                        table.getColumn(fieldName).setAsPrimaryKey();

                        break;
                    default:
                        throw new BadSqlException("Unknown block (" + currentBlock + "): " + line); // should never happen
                }
            }
        }
    }

    public static String diff(TSqlSchema lhs, TSqlSchema rhs) {
        // lhs: actual
        // rhs: expected

        // This only executes non-destructive updates
        //  - No drop table
        //  - No drop column
        //  - No change of PRIMARY KEY (that would require DROP and re-CREATE)

        StringBuilder builder = new StringBuilder();
        String prefix = "";

        List<Table> rhTables = rhs.getTables();

        for (Table tr : rhTables) {
            String rhTableName = tr.getName();

            if (lhs.hasTable(rhTableName)) {
                Table tl = lhs.getTable(rhTableName);
                List<Field> rhColumns = tr.getColumns();

                for (Field fr : rhColumns) {
                    String rhColumnName = fr.getName();

                    if (tl.hasColumn(rhColumnName)) {
                        Field fl = tl.getColumn(rhColumnName);

                        if (!fr.compareTo(fl)) {
                            builder.append(prefix);
                            builder.append("ALTER TABLE ");
                            builder.append(tr.getString(fac.renderer()));
                            builder.append(" ALTER COLUMN ");
                            builder.append(getColumnSchema(fr));

                            prefix = "\n";
                        }
                    } else {
                        builder.append(prefix);
                        builder.append("ALTER TABLE ");
                        builder.append(tr.getString(fac.renderer()));
                        builder.append(" ADD ");
                        builder.append(getColumnSchema(fr));

                        prefix = "\n";
                    }
                }
            } else {
                builder.append(prefix);
                builder.append(getTableSchema(tr));

                prefix = "\n";
            }
        }

        return builder.toString();
    }
}
