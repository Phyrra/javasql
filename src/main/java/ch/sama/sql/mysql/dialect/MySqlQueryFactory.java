package ch.sama.sql.mysql.dialect;

import ch.sama.sql.query.base.*;
import ch.sama.sql.query.helper.condition.IConditionRenderer;
import ch.sama.sql.query.helper.order.IOrderRenderer;

public class MySqlQueryFactory implements IQueryFactory {
    private IValueFactory value = new MySqlValueFactory();
    private ISourceFactory source = new MySqlSourceFactory();
    private IQueryRenderer renderer = new MySqlQueryRenderer();
    private IConditionRenderer condition = new MySqlConditionRenderer();
    private IOrderRenderer order = new MySqlOrderRenderer();

    @Override
    public IValueFactory value() {
        return value;
    }

    @Override
    public ISourceFactory source() {
        return source;
    }

    @Override
    public IQueryRenderer renderer() {
        return renderer;
    }

    @Override
    public IConditionRenderer condition() {
        return condition;
    }

    @Override
    public IOrderRenderer order() {
        return order;
    }

    @Override
    public Query query() {
        return new Query(renderer);
    }
}
