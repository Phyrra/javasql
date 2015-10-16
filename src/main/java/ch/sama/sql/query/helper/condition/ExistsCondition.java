package ch.sama.sql.query.helper.condition;

import ch.sama.sql.query.base.IQuery;

public class ExistsCondition implements ICondition {
    private IQuery query;

    public ExistsCondition(IQuery query) {
        this.query = query;
    }

    public IQuery getQuery() {
        return query;
    }

    @Override
    public String render(IConditionRenderer renderer) {
        return renderer.render(this);
    }
}
