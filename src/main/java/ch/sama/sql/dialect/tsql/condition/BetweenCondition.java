package ch.sama.sql.dialect.tsql.condition;

import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.CustomCondition;
import ch.sama.sql.query.helper.condition.IConditionRenderer;

class BetweenCondition extends CustomCondition {
    private Value start;
    private Value stop;

    public BetweenCondition(Value start, Value stop) {
        super(() -> "BETWEEN " + start.getValue() + " AND " + stop.getValue());

        this.start = start;
        this.stop = stop;
    }

    public Value getStart() {
        return start;
    }

    public Value getStop() {
        return stop;
    }
    
    @Override
    public String render(IConditionRenderer renderer) {
        return renderer.render(this);
    }
}
