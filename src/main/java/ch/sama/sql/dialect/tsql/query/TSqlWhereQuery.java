package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.WhereQuery;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.order.IOrder;

public class TSqlWhereQuery extends WhereQuery {
    private TSqlQueryRenderer renderer;

    public TSqlWhereQuery(TSqlQueryRenderer renderer, IQuery parent, ICondition condition) {
        super(renderer, parent, condition);

        this.renderer = renderer;
    }

    @Override
    public TSqlOrderQuery order(IOrder... o) {
        return new TSqlOrderQuery(renderer, this, o);
    }
}
