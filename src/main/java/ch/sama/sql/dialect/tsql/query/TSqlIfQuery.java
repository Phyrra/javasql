package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.helper.condition.ICondition;

public class TSqlIfQuery implements IQuery {
    private TSqlQueryRenderer renderer;
    private IQuery parent;
    private ICondition condition;
    private IQuery query;

    public TSqlIfQuery(TSqlQueryRenderer renderer, IQuery parent, ICondition condition, IQuery query) {
        this.renderer = renderer;
        this.parent = parent;
        this.condition = condition;
        this.query = query;
    }

    public ICondition getCondition() {
        return condition;
    }

    public IQuery getQuery() {
        return query;
    }

    @Override
    public IQuery getParent() {
        return parent;
    }

    @Override
    public String getSql() {
        return renderer.render(this);
    }

    @Override
    public IQuery chainTo(IQuery query) {
        this.parent = query;
        return query;
    }

    public TSqlElseQuery otherwise(IQuery query) {
        return new TSqlElseQuery(renderer, this, query);
    }
}
