package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.IConditionParser;
import ch.sama.sql.query.helper.IOrderParser;

public interface IQueryFactory {
    public IValueFactory value();
    public ISourceFactory source();
    public IQueryRenderer renderer();
    public IConditionParser condition();
    public IOrderParser order();
    public Query query();
}
