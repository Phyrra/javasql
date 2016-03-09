package ch.sama.sql.query.helper.condition;

import ch.sama.sql.query.helper.Value;

public class EqCondition implements ICondition {
    private Value lhs;
    private Value rhs;

    public EqCondition(Value lhs, Value rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Value getLhs() {
        return lhs;
    }

    public Value getRhs() {
        return rhs;
    }

    @Override
    public String render(IConditionRenderer renderer) {
        return renderer.render(this);
    }
}
