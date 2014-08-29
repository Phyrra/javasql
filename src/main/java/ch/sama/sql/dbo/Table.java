package ch.sama.sql.dbo;

import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.query.helper.Identifier;

public class Table {
    private String schema;
	private String table;
	private String alias;

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
            builder.append(schema);
            builder.append(".");
        }

        builder.append(table);

        if (alias != null) {
            builder.append(" AS ");
            builder.append(alias);
        }

		return builder.toString();
	}

    public Table as(String alias) {
        if (!Identifier.test(alias)) {
            throw new IllegalIdentifierException(alias);
        }

        this.alias = alias;
        return this;
    }
}