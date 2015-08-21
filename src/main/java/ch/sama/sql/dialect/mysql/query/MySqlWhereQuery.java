package ch.sama.sql.dialect.mysql.query;

import ch.sama.sql.dialect.mysql.MySqlQueryRenderer;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.WhereQuery;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.order.IOrder;

public class MySqlWhereQuery extends WhereQuery {
    private MySqlQueryRenderer renderer;

    public MySqlWhereQuery(MySqlQueryRenderer renderer, IQuery parent, ICondition condition) {
        super(renderer, parent, condition);

        this.renderer = renderer;
    }

    @Override
    public MySqlOrderQuery order(IOrder... o) {
        return new MySqlOrderQuery(renderer, this, o);
    }

    public MySqlLimitQuery limit(int limit) {
        return new MySqlLimitQuery(renderer, this, limit);
    }

    public MySqlLimitQuery limit(int start, int stop) {
        return new MySqlLimitQuery(renderer, this, start, stop);
    }
}
