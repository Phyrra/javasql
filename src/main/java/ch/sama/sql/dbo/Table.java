package ch.sama.sql.dbo;

import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.query.exception.ObjectNotFoundException;
import ch.sama.sql.query.base.checker.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table implements Cloneable {
    private String schema;
	private String table;
	private String alias;

    private String primaryKey;

    private Map<String, Field> columns = new HashMap<String, Field>();

	public Table(String table) {
        if (!Identifier.test(table)) {
            throw new IllegalIdentifierException(table);
        }

		this.table = table;
	}

    public Table(String schema, String table) {
        if (!Identifier.test(schema)) {
            throw new IllegalIdentifierException(schema);
        }
        if (!Identifier.test(table)) {
            throw new IllegalIdentifierException(table);
        }

        this.schema = schema;
        this.table = table;
    }
	
	public String toString() {
        StringBuilder builder = new StringBuilder();

        if (schema != null) {
            builder.append("[");
            builder.append(schema);
            builder.append("].");
        }

        builder.append("[");
        builder.append(table);
        builder.append("]");

        if (alias != null) {
            builder.append(" AS [");
            builder.append(alias);
            builder.append("]");
        }

		return builder.toString();
	}

    public Table as(String alias) {
        if (!Identifier.test(alias)) {
            throw new IllegalIdentifierException(alias);
        }

        Table table = null;
        try {
            table = (Table)this.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        table.alias = alias;
        return table;
    }

    public String getName() {
        return table;
    }

    public void addColumn(String field) {
        columns.put(field, new Field(this, field));
    }

    public void addColumn(Field field) {
        columns.put(field.getName(), field);
    }

    public Field getColumn(String name) {
        if (!columns.containsKey(name)) {
            throw new ObjectNotFoundException("Column " + name + " could not be found");
        }

        return columns.get(name);
    }

    public List<Field> getColumns() {
        return new ArrayList<Field>(columns.values());
    }

    public void setPrimaryKey(String name) {
        if (!columns.containsKey(name)) {
            throw new ObjectNotFoundException("Column " + name + " could not be found");
        }

        primaryKey = name;
    }

    public Field getPrimaryKey() {
        return getColumn(primaryKey);
    }

    public boolean isPrimaryKey(String name) {
        return primaryKey.equalsIgnoreCase(name);
    }
    public boolean isPrimaryKey(Field field) {
        return primaryKey.equalsIgnoreCase(field.getName());
    }
}