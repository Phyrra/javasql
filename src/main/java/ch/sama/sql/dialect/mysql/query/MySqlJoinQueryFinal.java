package ch.sama.sql.dialect.mysql.query;

import ch.sama.sql.dialect.mysql.MySqlQueryRenderer;
import ch.sama.sql.query.base.JoinQuery;
import ch.sama.sql.query.base.JoinQueryFinal;
import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.order.IOrder;

public class MySqlJoinQueryFinal extends JoinQueryFinal {
    private MySqlQueryRenderer renderer;

    public MySqlJoinQueryFinal(MySqlQueryRenderer renderer, JoinQuery parent, ICondition condition) {
        super(renderer, parent, condition);

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
