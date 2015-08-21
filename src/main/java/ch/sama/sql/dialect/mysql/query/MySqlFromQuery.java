package ch.sama.sql.dialect.mysql.query;

import ch.sama.sql.dialect.mysql.MySqlQueryRenderer;
import ch.sama.sql.query.base.FromQuery;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.order.IOrder;

public class MySqlFromQuery extends FromQuery {
    private MySqlQueryRenderer renderer;

    public MySqlFromQuery(MySqlQueryRenderer renderer, IQuery parent, Source[] sources) {
        super(renderer, parent, sources);

        this.renderer = renderer;
    }

    @Override
    public MySqlJoinQuery join(Source s) {
        return new MySqlJoinQuery(renderer, this, s);
    }

    @Override
    public MySqlOrderQuery order(IOrder... o) {
        return new MySqlOrderQuery(renderer, this, o);
    }

    @Override
    public MySqlWhereQuery where(ICondition c) {
        return new MySqlWhereQuery(renderer, this, c);
    }

    public MySqlLimitQuery limit(int limit) {
        return new MySqlLimitQuery(renderer, this, limit);
    }

    public MySqlLimitQuery limit(int start, int stop) {
        return new MySqlLimitQuery(renderer, this, start, stop);
    }
}