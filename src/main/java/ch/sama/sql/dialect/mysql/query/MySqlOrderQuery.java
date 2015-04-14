package ch.sama.sql.dialect.mysql.query;

import ch.sama.sql.dialect.mysql.MySqlQueryRenderer;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.OrderQuery;
import ch.sama.sql.query.helper.order.IOrder;

public class MySqlOrderQuery extends OrderQuery {
    private MySqlQueryRenderer renderer;

    public MySqlOrderQuery(MySqlQueryRenderer renderer, IQuery parent, IOrder[] orders) {
        super(renderer, parent, orders);

        this.renderer = renderer;
    }

    public MySqlLimitQuery limit(int limit) {
        return new MySqlLimitQuery(renderer, this, limit);
    }

    public MySqlLimitQuery limit(int start, int stop) {
        return new MySqlLimitQuery(renderer, this, start, stop);
    }
}