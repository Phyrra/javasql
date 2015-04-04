package ch.sama.sql.dialect.mysql;

import ch.sama.sql.query.base.IQueryBuilder;
import ch.sama.sql.query.base.Query;

public class MySqlQueryBuilder implements IQueryBuilder {
    private static final MySqlQueryFactory factory = new MySqlQueryFactory();
    private static final MySqlValueFactory value = new MySqlValueFactory();
    private static final MySqlSourceFactory source = new MySqlSourceFactory();
    private static final MySqlQueryRenderer renderer = new MySqlQueryRenderer();
    private static final MySqlConditionRenderer condition = new MySqlConditionRenderer();
    private static final MySqlOrderRenderer order = new MySqlOrderRenderer();

    @Override
    public MySqlQueryFactory factory() {
        return factory;
    }

    @Override
    public MySqlValueFactory value() {
        return value;
    }

    @Override
    public MySqlSourceFactory source() {
        return source;
    }

    @Override
    public MySqlQueryRenderer renderer() {
        return renderer;
    }

    @Override
    public MySqlConditionRenderer condition() {
        return condition;
    }

    @Override
    public MySqlOrderRenderer order() {
        return order;
    }

    @Override
    public Query query() {
        return factory.query();
    }
}
