package ch.sama.sql.dialect.tsql;

import ch.sama.sql.query.base.IQueryBuilder;
import ch.sama.sql.dialect.tsql.query.TSqlQuery;

public class TSqlQueryBuilder implements IQueryBuilder {
    private static final TSqlQueryFactory factory = new TSqlQueryFactory();
    private static final TSqlValueFactory value = new TSqlValueFactory();
    private static final TSqlSourceFactory source = new TSqlSourceFactory();
    private static final TSqlQueryRenderer renderer = new TSqlQueryRenderer();
    private static final TSqlConditionRenderer condition = new TSqlConditionRenderer();
    private static final TSqlOrderRenderer order = new TSqlOrderRenderer();

    @Override
    public TSqlQueryFactory factory() {
        return factory;
    }

    @Override
    public TSqlValueFactory value() {
        return value;
    }

    @Override
    public TSqlSourceFactory source() {
        return source;
    }

    @Override
    public TSqlQueryRenderer renderer() {
        return renderer;
    }

    @Override
    public TSqlConditionRenderer condition() {
        return condition;
    }

    @Override
    public TSqlOrderRenderer order() {
        return order;
    }

    @Override
    public TSqlQuery query() {
        return factory.query();
    }
}
