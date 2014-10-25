package ch.sama.sql.dbo;

import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.query.exception.ObjectNotFoundException;
import ch.sama.sql.query.base.checker.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Table {
    private String schema;
	private String table;

    private List<String> primaryKey;

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
	
	public abstract String toString();

    public String getName() {
        return table;
    }

    public String getSchema() {
        return schema;
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

    public boolean hasColumn(String name) {
        return columns.containsKey(name);
    }

    public List<Field> getColumns() {
        return new ArrayList<Field>(columns.values());
    }

    public void setPrimaryKey(String... keys) {
        primaryKey = new ArrayList<String>();

        for (String key : keys) {
            if (!columns.containsKey(key)) {
                throw new ObjectNotFoundException("Column " + key + " could not be found");
            }

            primaryKey.add(key);
        }
    }

    public List<Field> getPrimaryKey() {
        List<Field> key = new ArrayList<Field>();

        if (primaryKey == null) {
            return key;
        }

        for (String k : primaryKey) {
            key.add(getColumn(k));
        }

        return key;
    }

    public boolean isPrimaryKey(String name) {
        return primaryKey.contains(name);
    }
    public boolean isPrimaryKey(Field field) {
        return primaryKey.contains(field.getName());
    }
}