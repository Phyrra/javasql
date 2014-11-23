package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.base.*;
import ch.sama.sql.query.helper.condition.IConditionRenderer;
import ch.sama.sql.query.helper.order.IOrderRenderer;

public class TSqlQueryFactory implements IQueryFactory {
    private IValueFactory value = new TSqlValueFactory();
    private ISourceFactory source = new TSqlSourceFactory();
    private IQueryRenderer renderer = new TSqlQueryRenderer();
    private IConditionRenderer condition = new TSqlConditionRenderer(renderer);
    private IOrderRenderer order = new TSqlOrderRenderer();

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
