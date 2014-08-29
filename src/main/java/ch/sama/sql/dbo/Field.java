package ch.sama.sql.dbo;

import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.query.helper.Identifier;

public class Field {
    private Table table;
	private String tableName;
	private String field;
	
	public Field(String field) {
        if (!Identifier.test(field)) {
            throw new IllegalIdentifierException(field);
        }

		this.field = field;
	}
	
	public Field(String table, String field) {
        if (!Identifier.test(table)) {
            throw new IllegalIdentifierException(table);
        }

        if (!Identifier.test(field)) {
            throw new IllegalIdentifierException(field);
        }

		this.tableName = table;
		this.field = field;
	}

    public Field(Table table, String field) {
        if (!Identifier.test(field)) {
            throw new IllegalIdentifierException(field);
        }

        this.table = table;
        this.field = field;
    }
	
	public String toString() {
		StringBuilder builder = new StringBuilder();

		if (table != null) {
			builder.append(table.toString());
			builder.append(".");
		} else if (tableName != null) {
            builder.append(tableName);
            builder.append(".");
        }
		
		builder.append(field);
		
		return builder.toString();
	}
}