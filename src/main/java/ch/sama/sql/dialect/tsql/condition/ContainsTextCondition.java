package ch.sama.sql.dialect.tsql.condition;

import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.CustomCondition;
import ch.sama.sql.query.helper.condition.IConditionRenderer;

class ContainsTextCondition extends CustomCondition {
    private Value lhs;
    private String rhs;

    public ContainsTextCondition(Value lhs, String rhs) {
        super(() -> lhs.getValue() + " LIKE '%" + rhs.replace("'", "''") + "%'");

        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Value getLhs() {
        return lhs;
    }

    public String getRhs() {
        return rhs;
    }

    @Override
    public String render(IConditionRenderer renderer) {
        return renderer.render(this);
    }
}
