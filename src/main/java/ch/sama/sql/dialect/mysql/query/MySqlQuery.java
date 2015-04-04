package ch.sama.sql.dialect.mysql.query;

import ch.sama.sql.dialect.mysql.MySqlQueryFactory;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.Query;
import ch.sama.sql.query.helper.Value;

public class MySqlQuery extends Query {
    private MySqlQueryFactory factory;

    public MySqlQuery(MySqlQueryFactory factory) {
        super(factory);

        this.factory = factory;
    }

    public MySqlQuery(MySqlQueryFactory factory, IQuery parent) {
        super(factory, parent);

        this.factory = factory;
    }

    @Override
    public MySqlSelectQuery select(Value... v) {
        return factory.select(this, v);
    }
}
