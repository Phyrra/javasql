package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.Query;
import ch.sama.sql.query.helper.Value;

public class TSqlQuery extends Query {
    private TSqlQueryRenderer renderer;

    public TSqlQuery(TSqlQueryRenderer renderer) {
        super(renderer);

        this.renderer = renderer;
    }

    public TSqlQuery(TSqlQueryRenderer renderer, IQuery parent) {
        super(renderer, parent);

        this.renderer = renderer;
    }

    @Override
    public TSqlSelectQuery select(Value... v) {
        return new TSqlSelectQuery(renderer, this, v);
    }

    public TSqlCteQuery with(String alias) {
        return new TSqlCteQuery(renderer, this, alias);
    }
}
