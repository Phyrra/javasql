package ch.sama.sql.query.helper;

import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.query.exception.UnknownValueException;

public abstract class Value {
    public enum VALUE {
        ALL,
        NULL
    }

    public static final VALUE ALL = VALUE.ALL;
    public static final VALUE NULL = VALUE.NULL;

    private String alias;

	public abstract String toString();

    public String fromValue(Value.VALUE val) {
        switch (val) {
            case ALL:
                return allValue();
            case NULL:
                return nullValue();
            default:
                throw new UnknownValueException("Caused by: " + val);
        }
    }

    public abstract String allValue();
    public abstract String nullValue();

    public String getAlias() {
        return alias;
    }

    public Value as(String alias) {
        if (!Identifier.test(alias)) {
            throw new IllegalIdentifierException(alias);
        }

        this.alias = alias;
        return this;
    }
}