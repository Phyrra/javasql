package ch.sama.sql.dialect.sqlite;

import ch.sama.sql.query.base.IQueryFactory;
import ch.sama.sql.query.base.Query;

public class SqLiteQueryFactory implements IQueryFactory {
    private static final SqLiteQueryRenderer renderer = new SqLiteQueryRenderer();
    private static final SqLiteQueryCreator creator = new SqLiteQueryCreator();

    private static final SqLiteValueFactory value = new SqLiteValueFactory();
    private static final SqLiteSourceFactory source = new SqLiteSourceFactory();

    private static final SqLiteConditionRenderer condition = new SqLiteConditionRenderer();
    private static final SqLiteOrderRenderer order = new SqLiteOrderRenderer();

    @Override
    public SqLiteQueryRenderer renderer() {
        return renderer;
    }

    @Override
    public SqLiteQueryCreator creator() {
        return creator;
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
    public SqLiteConditionRenderer condition() {
        return condition;
    }

    @Override
    public SqLiteOrderRenderer order() {
        return order;
    }

    @Override
    public Query query() {
        return creator.query();
    }
}