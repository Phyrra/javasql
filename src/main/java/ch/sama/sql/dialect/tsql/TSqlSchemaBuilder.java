package ch.sama.sql.dialect.tsql;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.IType;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.dialect.tsql.type.TYPE;
import ch.sama.sql.jpa.*;
import ch.sama.sql.query.helper.Value;

import java.util.ArrayList;
import java.util.List;

public class TSqlSchemaBuilder {
    private static String renderTable(Table t) {
        String schema = t.getSchema();
        if (schema == null) {
            return "[" + t.getName() + "]";
        } else {
            return "[" + schema + "].["+  t.getName() + "]";
        }
    }

    public static String getTableSchema(Table table) {
        // TODO: Ignores FK constraints

        StringBuilder builder = new StringBuilder();
        String prefix = "";

        builder.append("CREATE TABLE ");
        builder.append(renderTable(table));
        builder.append(" (\n");

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

    private static String getColumnSchema(Field field) {
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

        if (field.isAutoIncrement()) {
            builder.append(" IDENTITY(1,1)");
        }

        if (field.isNullable()) {
            builder.append(" NULL");
        } else {
            builder.append(" NOT NULL");
        }

        Value defaultValue = field.getDefaultValue();
        if (defaultValue != null) {
            builder.append(" CONSTRAINT [DF_");
            builder.append(field.getTableName());
            builder.append("_");
            builder.append(field.getName());
            builder.append("] DEFAULT ");

            String defaultString = defaultValue.getValue();

            if (!(defaultString.startsWith("(") && defaultString.endsWith(")"))) {
                builder.append("(");
                builder.append(defaultString);
                builder.append(")");
            } else {
                builder.append(defaultString);
            }
        }

        return builder.toString();
    }

    public static <T> String getTableSchema(Class<T> clazz) {
        if (!clazz.isAnnotationPresent(Entity.class)) {
            throw new JpaException("Class must be annotated with @Entity");
        }

        StringBuilder builder = new StringBuilder();
        String prefix;

        String table = clazz.getAnnotation(Entity.class).name();
        if ("".equals(table)) {
            table = clazz.getSimpleName();
        }

        List<String> primaryKey = new ArrayList<String>();

        builder.append("CREATE TABLE [");
        builder.append(table);
        builder.append("] (\n");

        prefix = "";
        for (java.lang.reflect.Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                builder.append(prefix);
                builder.append("\t");

                Column col = field.getAnnotation(Column.class);
                String colName = col.name();

                builder.append(getColumnSchema(field, table));

                prefix = ",\n";

                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    primaryKey.add(colName);
                }
            }
        }

        if (primaryKey.isEmpty()) {
            builder.append("\n)");
        } else {
            builder.append(",\n\tCONSTRAINT [PK_");
            builder.append(table);
            builder.append("] PRIMARY KEY CLUSTERED (\n");

            prefix = "";
            for (String pKey : primaryKey) {
                builder.append(prefix);
                builder.append("\t\t[");
                builder.append(pKey);
                builder.append("] ASC");

                prefix = ",\n";
            }

            builder.append("\n\t)\n) ON [PRIMARY]");
        }

        return builder.toString();
    }

    private static String getColumnSchema(java.lang.reflect.Field field, String table) {
        StringBuilder builder = new StringBuilder();

        Column col = field.getAnnotation(Column.class);
        String colName = col.name();

        builder.append("[");
        builder.append(colName);
        builder.append("] [");

        Class fieldType = field.getType();
        IType type = TYPE.fromClass(fieldType);
        if (type == null) {
            throw new JpaException("Unknown field type: " + fieldType.getSimpleName());
        }

        String dataType = type.getString();
        if (dataType.contains("(")) {
            int idx = dataType.indexOf("(");

            builder.append(dataType.substring(0, idx));
            builder.append("]");
            builder.append(dataType.substring(idx));
        } else {
            builder.append(dataType);
            builder.append("]");
        }

        boolean autoIncr = field.isAnnotationPresent(AutoIncrement.class);
        if (autoIncr) {
            if (!TYPE.isEqualType(type, TYPE.INT_TYPE)) {
                throw new JpaException("AutoIncrement on non-integer column: " + colName);
            }

            builder.append(" IDENTITY(1,1)");
        }

        if (col.nullable()) {
            builder.append(" NULL");
        } else {
            builder.append(" NOT NULL");
        }

        if (!autoIncr && field.isAnnotationPresent(Default.class)) {
            Default def = field.getAnnotation(Default.class);

            builder.append(" CONSTRAINT [DF_");
            builder.append(table);
            builder.append("_");
            builder.append(colName);
            builder.append("] DEFAULT ");

            String defaultString = def.value();

            if (!(defaultString.startsWith("(") && defaultString.endsWith(")"))) {
                builder.append("(");
                builder.append(defaultString);
                builder.append(")");
            } else {
                builder.append(defaultString);
            }
        }

        return builder.toString();
    }

    public static String getSchemaDiff(TSqlSchema lhs, TSqlSchema rhs) {
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
                            builder.append(renderTable(tr));
                            builder.append(" ALTER COLUMN ");
                            builder.append(getColumnSchema(fr));

                            prefix = "\n";
                        }
                    } else {
                        builder.append(prefix);
                        builder.append("ALTER TABLE [");
                        builder.append(renderTable(tr));
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
