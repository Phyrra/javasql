package ch.sama.sql.dialect.tsql.schema;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.IType;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.dbo.schema.ISchemaDiff;
import ch.sama.sql.dbo.schema.ISchemaRenderer;
import ch.sama.sql.dbo.schema.SchemaUtil;
import ch.sama.sql.dialect.tsql.type.TYPE;
import ch.sama.sql.jpa.*;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.helper.Value;

import java.util.List;
import java.util.stream.Collectors;

public class TSqlSchemaRenderer implements ISchemaRenderer {
    private static final TSqlSchemaRenderer self = new TSqlSchemaRenderer();

    @Override
    public String renderName(Table table) {
        String schema = table.getSchema();
        if (schema == null) {
            return "[" + table.getName() + "]";
        } else {
            return "[" + schema + "].["+  table.getName() + "]";
        }
    }

    @Override
    public String renderName(Field field) {
        return "[" + field.getName() + "]";
    }

    @Override
    public String render(Table table) throws BadSqlException {
        // TODO: Ignores FK constraints

        StringBuilder builder = new StringBuilder();
        String prefix;

        builder.append("CREATE TABLE ");
        builder.append(renderName(table));
        builder.append(" (");

        prefix = "\n";
        for (Field field : table.getColumns()) {
            builder.append(prefix);
            builder.append("\t");
            builder.append(render(field));

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

    @Override
    public String render(Field field) throws BadSqlException {
        StringBuilder builder = new StringBuilder();

        String colName = field.getName();

        builder.append("[");
        builder.append(colName);
        builder.append("] [");

        IType type = field.getDataType();
        if (type == null) {
            throw new BadSqlException("Column " + colName + " has no type");
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

    public static String getSchemaDiff(TSqlSchema lhs, TSqlSchema rhs) {
        List<ISchemaDiff> diff = SchemaUtil.diff(lhs, rhs);

        return diff.stream()
                .map(d -> d.getString(self))
                .collect(Collectors.joining(";\n\n")) + ";";
    }

    public static String getTableSchema(Table table) throws BadSqlException {
        return self.render(table);
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

        return self.render(table);
    }
}
