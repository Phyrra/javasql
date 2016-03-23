package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.SelectQuery;
import ch.sama.sql.query.exception.BadParameterException;
import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.ICondition;

public class TSqlSelectQuery extends SelectQuery {
    private TSqlQueryRenderer renderer;
    private int top;

    public TSqlSelectQuery(TSqlQueryRenderer renderer, IQuery parent, Value[] values) {
        super(renderer, parent, values);

        this.top = -1;
        this.renderer = renderer;
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
        return renderer.render(this);
    }

    public int getTop() {
        return top;
    }

    public boolean hasTop() {
        return top > 0;
    }

    @Override
    public TSqlFromQuery from(Source... sources) {
        return new TSqlFromQuery(renderer, this, sources);
    }

    @Override
    public TSqlWhereQuery where(ICondition condition) {
        return new TSqlWhereQuery(renderer, this, condition);
    }
}
