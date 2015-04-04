package ch.sama.sql.dialect.mysql.query;

import ch.sama.sql.dialect.mysql.MySqlQueryFactory;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.SelectQuery;
import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.ICondition;

public class MySqlSelectQuery extends SelectQuery {
    private MySqlQueryFactory factory;

    public MySqlSelectQuery(MySqlQueryFactory factory, IQuery parent, Value[] v) {
        super(factory, parent, v);

        this.factory = factory;
    }

    @Override
    public MySqlFromQuery from(Source... s) {
        return factory.from(this, s);
    }

    @Override
    public MySqlQuery union() {
        return factory.query(this);
    }

    @Override
    public MySqlWhereQuery where(ICondition c) {
        return factory.where(this, c);
    }
}
