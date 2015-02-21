package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.condition.IConditionRenderer;
import ch.sama.sql.query.helper.order.IOrderRenderer;
import ch.sama.sql.query.helper.type.ITypeRenderer;

public interface IQueryFactory {
    public IValueFactory value();
    public ISourceFactory source();
    public IQueryRenderer renderer();
    public IConditionRenderer condition();
    public IOrderRenderer order();
    public ITypeRenderer type();
    public Query query();
}
