package ch.sama.sql.dialect.tsql.condition;

import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.CustomCondition;
import ch.sama.sql.query.helper.condition.IConditionRenderer;

class NotNullCondition extends CustomCondition {
    private Value value;

    public NotNullCondition(Value value) {
        super(() -> value.getValue() + " IS NOT NULL");

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
