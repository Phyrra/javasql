package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.query.base.IQuery;

public class TSqlElseQuery implements IQuery {
    private TSqlQueryRenderer renderer;
    private IQuery parent;
    private IQuery query;

    public TSqlElseQuery(TSqlQueryRenderer renderer, IQuery parent, IQuery query) {
        this.renderer = renderer;
        this.parent = parent;
        this.query = query;
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
}
