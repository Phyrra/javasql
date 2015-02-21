package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.base.*;
import ch.sama.sql.query.helper.condition.IConditionRenderer;
import ch.sama.sql.query.helper.order.IOrderRenderer;
import ch.sama.sql.query.helper.type.ITypeRenderer;

public class TSqlQueryFactory implements IQueryFactory {
    private IValueFactory value = new TSqlValueFactory();
    private ISourceFactory source = new TSqlSourceFactory();
    private IQueryRenderer renderer = new TSqlQueryRenderer();
    private IConditionRenderer condition = new TSqlConditionRenderer();
    private IOrderRenderer order = new TSqlOrderRenderer();
    private ITypeRenderer type = new TSqlTypeRenderer();

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
    public ITypeRenderer type() {
        return type;
    }

    @Override
    public Query query() {
        return new Query(renderer);
    }
}
