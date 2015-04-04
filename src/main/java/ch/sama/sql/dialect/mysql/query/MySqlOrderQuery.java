package ch.sama.sql.dialect.mysql.query;

import ch.sama.sql.dialect.mysql.MySqlQueryFactory;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.OrderQuery;
import ch.sama.sql.query.helper.order.IOrder;

public class MySqlOrderQuery extends OrderQuery {
    private MySqlQueryFactory factory;

    public MySqlOrderQuery(MySqlQueryFactory factory, IQuery parent, IOrder[] orders) {
        super(factory, parent, orders);

        this.factory = factory;
    }

    public MySqlLimitQuery limit(int start, int stop) {
        return factory.limit(this, start, stop);
    }
}
