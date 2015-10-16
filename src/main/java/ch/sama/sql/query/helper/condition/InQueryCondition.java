package ch.sama.sql.query.helper.condition;

import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.helper.Value;

public class InQueryCondition implements ICondition {
    private Value value;
    private IQuery query;

    public InQueryCondition(Value value, IQuery query) {
        this.value = value;
        this.query = query;
    }

    public Value getValue() {
        return value;
    }

    public IQuery getQuery() {
        return query;
    }
    
    @Override
    public String render(IConditionRenderer renderer) {
        return renderer.render(this);
    }
}
