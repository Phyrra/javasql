package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.base.*;
import ch.sama.sql.query.helper.IConditionParser;
import ch.sama.sql.query.helper.IOrderParser;

public class TSqlQueryFactory implements IQueryFactory {
    private IValueFactory value = new TSqlValueFactory();
    private ISourceFactory source = new TSqlSourceFactory();
    private IQueryRenderer renderer = new TSqlQueryRenderer();
    private IConditionParser condition = new TSqlConditionParser(renderer);
    private IOrderParser order = new TSqlOrderParser();

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
    public IConditionParser condition() {
        return condition;
    }

    @Override
    public IOrderParser order() {
        return order;
    }

    @Override
    public Query query() {
        return new Query(renderer);
    }
}
