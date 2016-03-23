package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.JoinQuery;
import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.condition.ICondition;

public class TSqlJoinQuery extends JoinQuery {
    private TSqlQueryRenderer renderer;

    public TSqlJoinQuery(TSqlQueryRenderer renderer, IQuery parent, Source source) {
        super(renderer, parent, source);

        this.renderer = renderer;
    }

    @Override
    public TSqlJoinQueryFinal on(ICondition c) {
        return new TSqlJoinQueryFinal(renderer, this, c);
    }
}
