package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.OrderQuery;
import ch.sama.sql.query.helper.order.IOrder;

public class TSqlOrderQuery extends OrderQuery {
    private TSqlQueryRenderer renderer;

    public TSqlOrderQuery(TSqlQueryRenderer renderer, IQuery parent, IOrder[] orders) {
        super(renderer, parent, orders);

        this.renderer = renderer;
    }

    public TSqlOffsetQuery offset(int offset) {
        return new TSqlOffsetQuery(renderer, this, offset);
    }

    public TSqlOffsetQuery limit(int offset, int limit) {
        return new TSqlOffsetQuery(renderer, this, offset, limit);
    }

    public TSqlOffsetQuery limit(int limit) {
        return new TSqlOffsetQuery(renderer, this, 0, limit);
    }
}