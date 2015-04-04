package ch.sama.sql.dialect.mysql.query;

import ch.sama.sql.dialect.mysql.MySqlQueryFactory;
import ch.sama.sql.query.base.JoinQuery;
import ch.sama.sql.query.base.JoinQueryFinal;
import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.order.IOrder;

public class MySqlJoinQueryFinal extends JoinQueryFinal {
    private MySqlQueryFactory factory;

    public MySqlJoinQueryFinal(MySqlQueryFactory factory, JoinQuery parent, ICondition condition) {
        super(factory, parent, condition);

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
}
