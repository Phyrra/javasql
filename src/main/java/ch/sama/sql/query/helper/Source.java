package ch.sama.sql.query.helper;

import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.base.check.Identifier;
import ch.sama.sql.query.exception.IllegalIdentifierException;

public class Source {
    private Object source;
    private String value;
    private String alias;

    public Source() { }

    public Source(Object o, String value) {
        this.source = o;
        this.value = value;
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

    public String getValue() {
        return value;
    }

    public String getString(IQueryRenderer renderer) {
        return renderer.render(this);
    }
}
