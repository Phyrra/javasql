package ch.sama.sql.dialect.mysql.query;

import ch.sama.sql.dialect.mysql.MySqlQueryFactory;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.JoinQuery;
import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.condition.ICondition;

public class MySqlJoinQuery extends JoinQuery {
    private MySqlQueryFactory factory;

    public MySqlJoinQuery(MySqlQueryFactory factory, IQuery parent, Source source) {
        super(factory, parent, source);

        this.factory = factory;
    }

    @Override
    public MySqlJoinQueryFinal on(ICondition c) {
        return factory.joinFinal(this, c);
    }
}
