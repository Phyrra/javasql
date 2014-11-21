package ch.sama.sql.query.helper;

import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.base.checker.Identifier;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.query.exception.UnknownValueException;

public class Value {
    public enum VALUE {
        ALL,
        NULL
    }

    public static final VALUE ALL = VALUE.ALL;
    public static final VALUE NULL = VALUE.NULL;

    private Object source;
    private String value;
    private String alias;

    public Value() { }

    public Value(Object o, String value) {
        source = o;
        this.value = value;
    }

	public String getValue() {
        return value;
    }

    public String getString(IQueryRenderer renderer) {
        return renderer.render(this);
    }

    public String getAlias() {
        return alias;
    }

    public Object getSource() {
        return source;
    }

    public Value as(String alias) {
        if (!Identifier.test(alias)) {
            throw new IllegalIdentifierException(alias);
        }

        this.alias = alias;
        return this;
    }
}