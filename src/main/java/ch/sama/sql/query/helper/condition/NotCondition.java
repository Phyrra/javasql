package ch.sama.sql.query.helper.condition;

public class NotCondition implements ICondition {
    private ICondition condition;

    public NotCondition(ICondition condition) {
        this.condition = condition;
    }

    public ICondition getCondition() {
        return condition;
    }

    @Override
    public String render(IConditionRenderer renderer) {
        return renderer.render(this);
    }
}
