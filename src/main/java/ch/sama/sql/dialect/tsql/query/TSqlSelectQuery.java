package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.query.exception.BadParameterException;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.SelectQuery;
import ch.sama.sql.dialect.tsql.TSqlQueryFactory;

public class TSqlSelectQuery extends SelectQuery {
    private TSqlQueryFactory factory;
    private int top;

    public TSqlSelectQuery(TSqlQueryFactory factory, IQuery parent, Value[] v) {
        super(factory, parent, v);

        this.top = -1;
        this.factory = factory;
    }

    public TSqlSelectQuery top(int n) {
        if (n <= 0) {
            throw new BadParameterException("Illegal top value: " + n);
        }

        this.top = n;
        return this;
    }

    @Override
    public String getSql() {
        return factory.renderer().render(this);
    }

    public int getTop() {
        return top;
    }

    public boolean hasTop() {
        return top > 0;
    }
}
