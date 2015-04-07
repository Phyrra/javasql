package ch.sama.sql.dialect.mysql.query;

import ch.sama.sql.dialect.mysql.MySqlQueryFactory;
import ch.sama.sql.query.base.FromQuery;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.order.IOrder;

public class MySqlFromQuery extends FromQuery {
    private MySqlQueryFactory factory;

    public MySqlFromQuery(MySqlQueryFactory factory, IQuery parent, Source[] sources) {
        super(factory, parent, sources);

        this.factory = factory;
    }

    @Override
    public MySqlJoinQuery join(Source s) {
        return factory.join(this, s);
    }

    @Override
    public MySqlOrderQuery order(IOrder... o) {
        return factory.order(this, o);
    }

    @Override
    public MySqlWhereQuery where(ICondition c) {
        return factory.where(this, c);
    }

    @Override
    public MySqlQuery union() {
        return factory.query(this);
    }

    public MySqlLimitQuery limit(int limit) {
        return factory.limit(this, limit);
    }

    public MySqlLimitQuery limit(int start, int stop) {
        return factory.limit(this, start, stop);
    }
}