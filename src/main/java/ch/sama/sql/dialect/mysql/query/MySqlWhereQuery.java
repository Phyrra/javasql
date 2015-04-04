package ch.sama.sql.dialect.mysql.query;

import ch.sama.sql.dialect.mysql.MySqlQueryFactory;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.WhereQuery;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.order.IOrder;

public class MySqlWhereQuery extends WhereQuery {
    private MySqlQueryFactory factory;

    public MySqlWhereQuery(MySqlQueryFactory factory, IQuery parent, ICondition condition) {
        super(factory, parent, condition);

        this.factory = factory;
    }

    public MySqlLimitQuery limit(int start, int stop) {
        return factory.limit(this, start, stop);
    }

    @Override
    public MySqlQuery union() {
        return factory.query(this);
    }

    @Override
    public MySqlOrderQuery order(IOrder... o) {
        return factory.order(this, o);
    }
}
