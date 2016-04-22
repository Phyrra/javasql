package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.condition.IConditionRenderer;
import ch.sama.sql.query.helper.order.IOrderRenderer;

/**
 * Factory to create everything necessary for creating queries
 */
public interface IQueryFactory {
    public IValueFactory value();
    public ISourceFactory source();
    public IQueryRenderer renderer();
    public IConditionRenderer condition();
    public IOrderRenderer order();

    public Query query();
}
