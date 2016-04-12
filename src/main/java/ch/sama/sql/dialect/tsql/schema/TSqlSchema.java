package ch.sama.sql.dialect.tsql.schema;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.dbo.connection.IQueryExecutor;
import ch.sama.sql.dbo.result.obj.ObjectTransformer;
import ch.sama.sql.dbo.schema.DbField;
import ch.sama.sql.dbo.schema.DbTable;
import ch.sama.sql.dbo.schema.ISchema;
import ch.sama.sql.dbo.schema.SchemaException;
import ch.sama.sql.dialect.tsql.TSqlFunctionFactory;
import ch.sama.sql.dialect.tsql.TSqlQueryFactory;
import ch.sama.sql.dialect.tsql.TSqlSourceFactory;
import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import ch.sama.sql.dialect.tsql.type.TYPE;
import ch.sama.sql.query.exception.BadParameterException;
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

    public TSqlSchema(IQueryExecutor executor) {
        loadSchema(executor, table -> true);
    }

    public TSqlSchema(IQueryExecutor executor, Function<String, Boolean> filter) {
        loadSchema(executor, filter);
    }

    private void loadSchema(IQueryExecutor executor, Function<String, Boolean> filter) {
        TSqlFunctionFactory fnc = new TSqlFunctionFactory();

        tables = new HashMap<String, Table>();

        List<DbTable> result = executor.query(
                sql.query()
                        .select(
                                value.field("TABLE_SCHEMA"),
                                value.field("TABLE_NAME")
                        )
                        .from(source.table("INFORMATION_SCHEMA", "TABLES"))
                .getSql(),
                new ObjectTransformer<DbTable>(DbTable.class)
        );

        for (DbTable table : result) {
            String schema = table.getSchema();
            String tableName = table.getName();

            if (!filter.apply(tableName)) {
                continue;
            }

            logger.debug("Creating table: " + tableName);

            Table tbl = new Table(schema, tableName);

            List<DbField> columns = executor.query(
                    sql.query()
                            .select(
                                    value.field("COLUMN_NAME"),
                                    value.field("DATA_TYPE"),
                                    value.field("CHARACTER_MAXIMUM_LENGTH"),
                                    fnc.caseWhen(
                                            fnc.whenThen(Condition.eq(value.field("IS_NULLABLE"), value.string("YES")), value.numeric(1)),
                                            fnc.otherwise(value.numeric(0))
                                    ).as("IS_NULLABLE"),
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
                                                                            Condition.eq(value.field("tc", "TABLE_NAME"), value.string(tableName)),
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
                                    ).as("IS_AUTO_INCREMENT")
                            )
                            .from(source.table("INFORMATION_SCHEMA", "COLUMNS"))
                            .where(Condition.eq(value.field("TABLE_NAME"), value.string(tableName)))
                    .getSql(),
                    new ObjectTransformer<DbField>(DbField.class)
            );

            for (DbField column : columns) {
                Field f = new Field(tbl, column.getName());

                String dataType = column.getType();
                Integer maxLength = column.getCharMaxLength();
                if (maxLength != null) {
                    if (maxLength == -1) {
                        dataType += "(max)";
                    } else {
                        dataType += "(" + maxLength + ")";
                    }
                }
                f.setDataType(TYPE.fromString(dataType));

                if (column.isNullable()) {
                    f.setNullable();
                } else {
                    f.setNotNullable();
                }

                String defaultValue = column.getDefaultValue();
                if (defaultValue != null) {
                    // The data type is inconsequential here
                    f.setDefaultValue(value.plain(defaultValue));
                }

                tbl.addColumn(f);

                if (column.isPrimaryKey()) {
                    f.setAsPrimaryKey();
                }

                if (column.isAutoIncrement()) {
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
            throw new BadParameterException(e.getMessage(), e);
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

    private void throwSchemaException(String message, int lineNo, String line) {
        throw new SchemaException(message + " on line #" + lineNo + ": " + line);
    }

    private void parse(String schema) throws SchemaException {
        // This will be very basic!
        //  I have no intention of writing a complete SQL parser

        final int NONE = 0;
        final int TABLE_BLOCK = 1;
        final int PRIMARY_BLOCK = 2;

        final Pattern TABLE_PATTERN = Pattern.compile("(\\[(\\w+)\\]\\.)?\\[(\\w+)\\]");
        final Pattern FIELD_PATTERN = Pattern.compile("\\[(\\w+)\\](\\((\\d+|[m|M][a|A][x|X])\\))?");
        final String IDENTITY_REGEX = "(?i).*IDENTITY\\(\\d\\s*,\\s*\\d\\).*";
        final String NOTNULL_REGEX = "(?i).*NOT NULL.*";
        final Pattern DEFAULT_PATTERN = Pattern.compile("(?i)CONSTRAINT\\s+\\[\\w+\\]\\s+DEFAULT\\s+.+");

        tables = new HashMap<String, Table>();
        int currentBlock = NONE;
        Table table = null;

        int lineNo = 0;

        String[] lines = schema.split("\n");
        for (String line : lines) {
            ++lineNo;
            line = line.trim();

            if (line.startsWith("CREATE TABLE")) {
                currentBlock = TABLE_BLOCK;

                Matcher matcher = TABLE_PATTERN.matcher(line);

                if (matcher.find()) {
                    String tableSchema = matcher.group(2);
                    String tableName = matcher.group(3);

                    if (tableSchema == null) {
                        table = new Table(tableName);
                    } else {
                        table = new Table(tableSchema, tableName);
                    }

                    addTable(table);
                } else {
                    throwSchemaException("No table name found", lineNo, line);
                }
            } else if (line.startsWith("CONSTRAINT")) { // the only supported constraint is PRIMARY
                currentBlock = PRIMARY_BLOCK;
            } else if (line.startsWith(")")) {
                switch (currentBlock) {
                    case NONE:
                        throwSchemaException("Unbalanced Brackets", lineNo, line);
                    case TABLE_BLOCK:
                        table = null;
                        currentBlock = NONE;

                        break;
                    case PRIMARY_BLOCK:
                        if (table == null) {
                            throwSchemaException("Primary key block without table", lineNo, line);
                        }

                        currentBlock = TABLE_BLOCK;

                        break;
                    default:
                        throwSchemaException("Unknown block", lineNo, line);
                }
            } else if (line.equals("(")) {
                // ignore
            } else if (line.startsWith("--") || line.length() == 0) {
                // ignore
            } else {
                Matcher matcher = FIELD_PATTERN.matcher(line);
                String fieldName = null;
                int i = 0;

                switch (currentBlock) {
                    case NONE:
                        throwSchemaException("Blockless field", lineNo, line);
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
                            throwSchemaException("No field name in TABLE_BLOCK", lineNo, line);
                        }

                        Field field = new Field(table, fieldName);

                        if (line.matches(IDENTITY_REGEX)) {
                            field.setAutoIncrement();
                        } else {
                            field.removeAutoIncrement();
                        }

                        if (line.matches(NOTNULL_REGEX)) {
                            field.setNotNullable();
                        } else {
                            field.setNullable(); // nullable = true is default if none is set
                        }

                        if (dataType != null) {
                            field.setDataType(TYPE.fromString(dataType));
                        }

                        Matcher defaultMatcher = DEFAULT_PATTERN.matcher(line);
                        if (defaultMatcher.find()) {
                            int matchStart = defaultMatcher.start();

                            int start = line.indexOf("(", matchStart);
                            int open = 1;

                            int stop = start + 1;
                            while (open > 0 && stop < line.length()) {
                                char c = line.charAt(stop);

                                if (c == '(') {
                                    ++open;
                                } else if (c == ')') {
                                    --open;
                                }

                                ++stop;
                            }

                            if (open > 0) {
                                throwSchemaException("Unbalanced brackets in default value", lineNo, line);
                            }

                            String defaultValue = line.substring(start + 1, stop - 1);
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
                            throwSchemaException("No field name in PRIMARY_BLOCK", lineNo, line);
                        }

                        if (!table.hasColumn(fieldName)) {
                            throwSchemaException("Primary key error, table has no column " + fieldName, lineNo, line);
                        }

                        table.getColumn(fieldName).setAsPrimaryKey();

                        break;
                    default:
                        throwSchemaException("Unknown block " + currentBlock, lineNo, line); // should never happen
                }
            }
        }
    }
}
