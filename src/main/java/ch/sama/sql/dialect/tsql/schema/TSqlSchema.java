package ch.sama.sql.dialect.tsql.schema;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.schema.ISchema;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.dbo.connection.IQueryExecutor;
import ch.sama.sql.dbo.result.map.MapResult;
import ch.sama.sql.dialect.tsql.TSqlFunctionFactory;
import ch.sama.sql.dialect.tsql.TSqlQueryFactory;
import ch.sama.sql.dialect.tsql.TSqlSourceFactory;
import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import ch.sama.sql.dialect.tsql.type.TYPE;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.exception.ObjectNotFoundException;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TSqlSchema implements ISchema {
    private static final Logger logger = LoggerFactory.getLogger(TSqlSchema.class);

    private static final TSqlQueryFactory sql = new TSqlQueryFactory();
    private static final TSqlValueFactory value = sql.value();
    private static final TSqlSourceFactory source = sql.source();

    private Map<String, Table> tables;

    public TSqlSchema(Map<String, Table> tables) {
        this.tables = tables;
    }

    public TSqlSchema(IQueryExecutor<List<MapResult>> executor) {
        loadSchema(executor, table -> true);
    }

    public TSqlSchema(IQueryExecutor<List<MapResult>> executor, Function<String, Boolean> filter) {
        loadSchema(executor, filter);
    }

    private void loadSchema(IQueryExecutor<List<MapResult>> executor, Function<String, Boolean> filter) {
        TSqlFunctionFactory fnc = new TSqlFunctionFactory();

        tables = new HashMap<String, Table>();

        List<MapResult> result = executor.query(
                sql.query()
                        .select(
                                value.field("TABLE_SCHEMA"),
                                value.field("TABLE_NAME")
                        )
                        .from(source.table("INFORMATION_SCHEMA", "TABLES"))
                .getSql()
        );

        for (MapResult row : result) {
            String schema = row.getAsString("TABLE_SCHEMA");
            String table = row.getAsString("TABLE_NAME");

            if (!filter.apply(table)) {
                continue;
            }

            logger.debug("Creating table: " + table);

            Table tbl = new Table(schema, table);

            List<MapResult> columns = executor.query(
                    sql.query()
                            .select(
                                    value.field("COLUMN_NAME"),
                                    value.field("DATA_TYPE"),
                                    value.field("CHARACTER_MAXIMUM_LENGTH"),
                                    value.field("IS_NULLABLE"),
                                    value.field("COLUMN_DEFAULT"),
                                    fnc.coalesce(
                                            value.query(
                                                    sql.query()
                                                            .select(value.numeric(1))
                                                            .from(source.table("INFORMATION_SCHEMA", "TABLE_CONSTRAINTS").as("tc"))
                                                            .join(source.table("INFORMATION_SCHEMA", "CONSTRAINT_COLUMN_USAGE").as("ccu")).on(Condition.eq(value.field("tc", "CONSTRAINT_NAME"), value.field("ccu", "CONSTRAINT_NAME")))
                                                            .where(
                                                                    Condition.and(
                                                                            Condition.eq(value.field("tc", "CONSTRAINT_TYPE"), value.string("PRIMARY KEY")),
                                                                            Condition.eq(value.field("tc", "TABLE_NAME"), value.string(table)),
                                                                            Condition.eq(value.field("ccu", "COLUMN_NAME"), value.field("COLUMNS", "COLUMN_NAME"))
                                                                    )
                                                            )
                                            ),
                                            value.numeric(0)
                                    ).as("IS_PKEY"),
                                    value.function(
                                            "columnproperty",
                                            value.function("object_id", value.field("TABLE_NAME")),
                                            value.field("COLUMN_NAME"),
                                            value.string("IsIdentity")
                                    ).as("IS_IDENTITY")
                            )
                            .from(source.table("INFORMATION_SCHEMA", "COLUMNS"))
                            .where(Condition.eq(value.field("TABLE_NAME"), value.string(table)))
                    .getSql()
            );

            for (MapResult column : columns) {
                Field f = new Field(tbl, column.getAsString("COLUMN_NAME"));

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
                    f.setNotNullable();
                } else {
                    f.setNullable();
                }

                String defaultValue = column.getAsString("COLUMN_DEFAULT");
                if (defaultValue != null) {
                    // The data type is inconsequential here
                    f.setDefaultValue(value.plain(defaultValue));
                }

                tbl.addColumn(f);

                if (column.getAsInt("IS_PKEY") == 1) {
                    f.setAsPrimaryKey();
                }

                if (column.getAsInt("IS_IDENTITY") == 1) {
                    f.setAutoIncrement();
                }
            }

            addTable(tbl);
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

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                if (builder.length() > 0) {
                    builder.append("\n");
                }

                builder.append(line);
            }

            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        parse(builder.toString());
    }

    private void addTable(Table table) {
        tables.put(table.getName(), table);
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

                addTable(table);
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
                            field.setNotNullable();
                        } else {
                            field.setNullable(); // nullable = true is default if none is set
                        }

                        if (dataType != null) {
                            field.setDataType(TYPE.fromString(dataType));
                        }

                        if (line.contains("CONSTRAINT") && line.contains("DEFAULT")) { // The only supported constraint is DEFAULT
                            int idx0 = line.indexOf("DEFAULT");
                            int idx1 = line.indexOf("(", idx0 + 1);
                            int idx2 = line.lastIndexOf(")");

                            String defaultValue = line.substring(idx1, idx2 + 1);
                            field.setDefaultValue(new Value(defaultValue));
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

                        if (!table.hasColumn(fieldName)) {
                            throw new BadSqlException("Primary key error, table has no " + fieldName);
                        }

                        table.getColumn(fieldName).setAsPrimaryKey();

                        break;
                    default:
                        throw new BadSqlException("Unknown block (" + currentBlock + "): " + line); // should never happen
                }
            }
        }
    }
}
