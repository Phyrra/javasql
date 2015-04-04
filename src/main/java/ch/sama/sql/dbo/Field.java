package ch.sama.sql.dbo;

import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.base.check.Identifier;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.query.helper.Value;

public class Field {
    private Table table;
	private String field;

    private IType dataType;
    private Value defaultValue;

    private boolean isNullable = true;
    private boolean isPrimaryKey = false;
    private boolean isAutoIncrement = false;
	
	public Field(String field) {
        if (!Identifier.test(field)) {
            throw new IllegalIdentifierException(field);
        }

		this.field = field;
	}

    public Field(String table, String field) {
        this(field);

        this.table = new Table(table);
    }

    public Field(Table table, String field) {
        this(field);

        this.table = table;
    }

    public Field chainType(IType type) {
        this.dataType = type;

        return this;
    }

    public Field chainNullable(boolean nullable) {
        this.isNullable = nullable;

        return this;
    }

    public Field chainPrimaryKey(boolean primaryKey) {
        this.isPrimaryKey = primaryKey;

        return this;
    }

    public Field chainAutoIncrement(boolean autoIncr) {
        this.isAutoIncrement = autoIncr;

        return this;
    }

    public Field chainDefaultValue(Value defaultValue) {
        this.defaultValue = defaultValue;

        return this;
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

    public void setNullable() {
        this.isNullable = true;
    }

    public void setNotNullable() {
        this.isNullable = false;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public void setDefaultValue(Value val) {
        defaultValue = val;
    }

    public Value getDefaultValue() {
        return defaultValue;
    }

    public boolean hasDefaultValue() {
        return defaultValue != null;
    }

    public Table getTable() {
        return table;
    }

    public String getTableName() {
        if (table == null) {
            return null;
        }

        return table.getName();
    }

    public void setAsPrimaryKey() {
        isPrimaryKey = true;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setAutoIncrement() {
        isAutoIncrement = true;
    }

    public boolean isAutoIncrement() {
        return isAutoIncrement;
    }

    public boolean compareTo(Field other) {
        if (isNullable != other.isNullable()) {
            return false;
        }

        if (isAutoIncrement() != other.isAutoIncrement()) {
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

        Value otherDefault = other.getDefaultValue();
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