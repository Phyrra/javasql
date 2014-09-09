package ch.sama.sql.dbo;

import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.query.base.checker.Identifier;
import ch.sama.sql.query.helper.Value;

public class Field {
    private Table table;
	private String tableName;
	private String field;

    private String dataType;
    private boolean nullable = true;
    private Value defaultValue;
	
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

    public String getName() {
        return field;
    }
	
	public String toString() {
		StringBuilder builder = new StringBuilder();

		if (table != null) {
			builder.append(table.toString());
			builder.append(".");
		} else if (tableName != null) {
            builder.append("[");
            builder.append(tableName);
            builder.append("].");
        }

        builder.append("[");
		builder.append(field);
        builder.append("]");
		
		return builder.toString();
	}

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean getNullable() {
        return nullable;
    }

    public void setDefault(Value val) {
        this.defaultValue = val;
    }

    public Value getDefault() {
        return this.defaultValue;
    }

    public Table getTable() {
        return this.table;
    }

    public boolean compareTo(Field other) {
        if (nullable != other.getNullable()) {
            return false;
        }

        String otherType = other.getDataType();
        if (dataType == null && otherType != null || dataType != null && otherType == null) {
            return false;
        }
        if (dataType != null && otherType != null) {
            if (!dataType.equals(otherType)) {
                return false;
            }
        }

        Value otherDefault = other.getDefault();
        if (defaultValue == null && otherDefault != null || defaultValue != null && otherDefault == null) {
            return false;
        }
        if (defaultValue != null && otherDefault != null) {
            if (!defaultValue.toString().equals(otherDefault.toString())) {
                return false;
            }
        }

        return true;
    }
}