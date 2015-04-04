package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.condition.IConditionRenderer;
import ch.sama.sql.query.helper.order.IOrderRenderer;

public interface IQueryBuilder {
    public IQueryFactory factory();
    public IValueFactory value();
    public ISourceFactory source();
    public IQueryRenderer renderer();
    public IConditionRenderer condition();
    public IOrderRenderer order();

    public Query query();
}
