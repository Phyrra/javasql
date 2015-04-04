package ch.sama.sql.dialect.tsql;

import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.base.*;
import ch.sama.sql.query.standard.QueryFactory;
import ch.sama.sql.dialect.tsql.query.TSqlCteQuery;
import ch.sama.sql.dialect.tsql.query.TSqlQuery;
import ch.sama.sql.dialect.tsql.query.TSqlSelectQuery;

public class TSqlQueryFactory extends QueryFactory {
    private static final TSqlQueryRenderer renderer = new TSqlQueryRenderer();

    @Override
    public TSqlQueryRenderer renderer() {
        return renderer;
    }

    @Override
    public TSqlQuery query() {
        return new TSqlQuery(this);
    }

    @Override
    public TSqlQuery query(IQuery parent) {
        return new TSqlQuery(this, parent);
    }

    @Override
    public TSqlSelectQuery select(IQuery parent, Value[] v) {
        return new TSqlSelectQuery(this, parent, v);
    }

    public TSqlCteQuery with(IQuery parent, String name) {
        return new TSqlCteQuery(this, parent, name);
    }
}
