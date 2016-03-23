package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.query.base.JoinQuery;
import ch.sama.sql.query.base.JoinQueryFinal;
import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.order.IOrder;

public class TSqlJoinQueryFinal extends JoinQueryFinal {
    private TSqlQueryRenderer renderer;

    public TSqlJoinQueryFinal(TSqlQueryRenderer renderer, JoinQuery parent, ICondition condition) {
        super(renderer, parent, condition);

        this.renderer = renderer;
    }

    @Override
    public TSqlJoinQuery join(Source s) {
        return new TSqlJoinQuery(renderer, this, s);
    }

    @Override
    public TSqlOrderQuery order(IOrder... o) {
        return new TSqlOrderQuery(renderer, this, o);
    }

    @Override
    public TSqlWhereQuery where(ICondition c) {
        return new TSqlWhereQuery(renderer, this, c);
    }
}
