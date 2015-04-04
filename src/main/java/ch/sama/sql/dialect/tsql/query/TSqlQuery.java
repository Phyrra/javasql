package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.Query;
import ch.sama.sql.dialect.tsql.TSqlQueryFactory;

public class TSqlQuery extends Query {
    private TSqlQueryFactory factory;

    public TSqlQuery(TSqlQueryFactory factory) {
        super(factory);

        this.factory = factory;
    }

    public TSqlQuery(TSqlQueryFactory factory, IQuery parent) {
        super(factory, parent);

        this.factory = factory;
    }

    @Override
    public TSqlSelectQuery select(Value... v) {
        return factory.select(this, v);
    }

    public TSqlCteQuery with(String alias) {
        return factory.with(this, alias);
    }
}
