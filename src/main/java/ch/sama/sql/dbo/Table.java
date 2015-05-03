package ch.sama.sql.dbo;

import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.base.check.Identifier;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.query.exception.ObjectNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

public class Table {
    private String schema;
	private String table;

    private Map<String, Field> columns = new LinkedHashMap<String, Field>();

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

    public List<Field> getPrimaryKey() {
        return columns.values().stream()
                .filter(Field::isPrimaryKey)
                .collect(Collectors.toList());
    }

    public boolean hasPrimaryKey() {
        return !getPrimaryKey().isEmpty();
    }

    public String getString(IQueryRenderer renderer) {
        return renderer.render(this);
    }
}