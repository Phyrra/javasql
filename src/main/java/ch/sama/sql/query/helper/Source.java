package ch.sama.sql.query.helper;

import ch.sama.sql.query.base.checker.Identifier;
import ch.sama.sql.query.exception.IllegalIdentifierException;

public abstract class Source {
    private Object source;
    private String alias;

    public Source() { }

    public Source(Object o) {
        this.source = o;
    }

    public String getAlias() {
        return alias;
    }

    public Object getSource() {
        return source;
    }

    public Source as(String alias) {
        if (!Identifier.test(alias)) {
            throw new IllegalIdentifierException(alias);
        }

        this.alias = alias;
        return this;
    }

    public abstract String getString();
}
