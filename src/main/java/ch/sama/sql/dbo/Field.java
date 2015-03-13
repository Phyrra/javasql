package ch.sama.sql.dbo;

import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.query.base.check.Identifier;
import ch.sama.sql.query.helper.Value;

public class Field {
    private Table table;
	private String field;

    private IType dataType;
    private boolean nullable = true;
    private Value defaultValue;
    private boolean isPrivateKey = false;
	
	public Field(String field) {
        if (!Identifier.test(field)) {
            throw new IllegalIdentifierException(field);
        }

		this.field = field;
	}

    public Field(String field, IType type) {
        this(field);

        this.dataType = type;
    }

    public Field(String table, String field) {
        this(field);

        this.table = new Table(table);
    }

    public Field(Table table, String field) {
        this(field);

        this.table = table;
    }

    public Field(String table, String field, IType type) {
        this(table, field);

        this.dataType = type;
    }

    public Field(Table table, String field, IType type) {
        this(table, field);

        this.dataType = type;
    }

    public String getName() {
        return field;
    }

    public void setDataType(IType dataType) {
        this.dataType = dataType;
    }

    public IType getDataType() {
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

    public String getTableName() {
        if (table == null) {
            return null;
        }

        return table.getName();
    }

    public void setAsPrimaryKey() {
        isPrivateKey = true;
    }

    public boolean isPrimaryKey() {
        return isPrivateKey;
    }

    public boolean compareTo(Field other) {
        if (nullable != other.getNullable()) {
            return false;
        }

        IType otherType = other.getDataType();
        if (dataType == null && otherType != null || dataType != null && otherType == null) {
            return false;
        }
        if (dataType != null && otherType != null) {
            if (!dataType.getClass().equals(otherType.getClass())) {
                return false;
            }
        }

        Value otherDefault = other.getDefault();
        if (defaultValue == null && otherDefault != null || defaultValue != null && otherDefault == null) {
            return false;
        }
        if (defaultValue != null && otherDefault != null) {
            if (!defaultValue.getValue().equals(otherDefault.getValue())) {
                return false;
            }
        }

        return true;
    }

    public String getString(IQueryRenderer renderer){
        return renderer.render(this);
    }
}