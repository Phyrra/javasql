package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.query.base.IQuery;

public class TSqlCteQueryFinal extends MainQuery {
    private TSqlCteQuery parent;
    private IQuery query;

    public TSqlCteQueryFinal(TSqlQueryRenderer renderer, TSqlCteQuery parent, IQuery query) {
        super(renderer);

        this.parent = parent;
        this.query = query;
    }

    @Override
    public IQuery getParent() {
        return parent;
    }

    @Override
    public String getSql() {
        return getRenderer().render(this);
    }

    @Override
    public IQuery chainTo(IQuery query) {
        return this.parent.chainTo(query);
    }

    public String getCteName() {
        return parent.getName();
    }

    public IQuery getQuery() {
        return query;
    }
}
