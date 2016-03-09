package ch.sama.sql.query.helper.condition;

import ch.sama.sql.query.helper.Value;

public class IsNullCondition implements ICondition {
    private Value value;

    public IsNullCondition(Value value) {
        this.value = value;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String render(IConditionRenderer renderer) {
        return renderer.render(this);
    }
}
