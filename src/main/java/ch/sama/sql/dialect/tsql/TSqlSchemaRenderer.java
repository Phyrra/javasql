package ch.sama.sql.dialect.tsql;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.IType;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.dialect.tsql.type.TYPE;
import ch.sama.sql.jpa.*;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.helper.Value;

import java.util.List;

public class TSqlSchemaRenderer {
    private static String renderTableName(Table t) {
        String schema = t.getSchema();
        if (schema == null) {
            return "[" + t.getName() + "]";
        } else {
            return "[" + schema + "].["+  t.getName() + "]";
        }
    }

    public static String getTableSchema(Table table) throws BadSqlException {
        // TODO: Ignores FK constraints

        StringBuilder builder = new StringBuilder();
        String prefix = "";

        builder.append("CREATE TABLE ");
        builder.append(renderTableName(table));
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

        String colName = field.getName();

        builder.append("[");
        builder.append(colName);
        builder.append("] [");

        IType type = field.getDataType();

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

        if (field.isAutoIncrement()) {
            if (!TYPE.isWeakEqualType(type, TYPE.INT_TYPE)) {
                throw new BadSqlException("AutoIncrement on non-integer column: " + colName);
            }

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

    public static <T> String getTableSchema(Class<T> clazz) throws JpaException, BadSqlException {
        if (!clazz.isAnnotationPresent(Entity.class)) {
            throw new JpaException("Class must be annotated with @Entity");
        }

        Entity entity = clazz.getAnnotation(Entity.class);
        Table table;

        String name = entity.name();
        if ("".equals(name)) {
            name = clazz.getSimpleName();
        }

        String schema = entity.schema();
        if ("".equals(schema)) {
            table = new Table(name);
        } else {
            table = new Table(schema, name);
        }

        for (java.lang.reflect.Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Column col = field.getAnnotation(Column.class);
                String colName = col.name();

                Field column = new Field(table, colName);

                IType type = TYPE.fromClass(field.getType());
                column.setDataType(type);

                if (col.nullable()) {
                    column.setNullable();
                } else {
                    column.setNotNullable();
                }

                if (field.isAnnotationPresent(AutoIncrement.class)) {
                    if (!TYPE.isWeakEqualType(type, TYPE.INT_TYPE)) {
                        throw new BadSqlException("AutoIncrement on non-integer column: " + colName);
                    }

                    column.setAutoIncrement();
                }

                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    column.setAsPrimaryKey();
                }

                if (field.isAnnotationPresent(Default.class)) {
                    column.setDefaultValue(new Value(field.getAnnotation(Default.class).value()));
                }

                table.addColumn(column);
            }
        }

        return getTableSchema(table);
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
                            builder.append(renderTableName(tr));
                            builder.append(" ALTER COLUMN ");
                            builder.append(getColumnSchema(fr));

                            prefix = "\n";
                        }
                    } else {
                        builder.append(prefix);
                        builder.append("ALTER TABLE [");
                        builder.append(renderTableName(tr));
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
