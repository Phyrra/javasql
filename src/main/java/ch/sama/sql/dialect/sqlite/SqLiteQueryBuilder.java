package ch.sama.sql.dialect.sqlite;

import ch.sama.sql.query.base.IQueryBuilder;
import ch.sama.sql.query.base.Query;

public class SqLiteQueryBuilder implements IQueryBuilder {
    private static final SqLiteQueryFactory factory = new SqLiteQueryFactory();
    private static final SqLiteValueFactory value = new SqLiteValueFactory();
    private static final SqLiteSourceFactory source = new SqLiteSourceFactory();
    private static final SqLiteQueryRenderer renderer = new SqLiteQueryRenderer();
    private static final SqLiteConditionRenderer condition = new SqLiteConditionRenderer();
    private static final SqLiteOrderRenderer order = new SqLiteOrderRenderer();

    @Override
    public SqLiteQueryFactory factory() {
        return factory;
    }

    @Override
    public SqLiteValueFactory value() {
        return value;
    }

    @Override
    public SqLiteSourceFactory source() {
        return source;
    }

    @Override
    public SqLiteQueryRenderer renderer() {
        return renderer;
    }

    @Override
    public SqLiteConditionRenderer condition() {
        return condition;
    }

    @Override
    public SqLiteOrderRenderer order() {
        return order;
    }

    @Override
    public Query query() {
        return factory.query();
    }
}