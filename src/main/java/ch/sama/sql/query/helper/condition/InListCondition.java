package ch.sama.sql.query.helper.condition;

import ch.sama.sql.query.helper.Value;

import java.util.List;

public class InListCondition implements ICondition {
    private Value value;
    private List<Value> list;

    public InListCondition(Value value, List<Value> list) {
        this.value = value;
        this.list = list;
    }

    public Value getValue() {
        return value;
    }

    public List<Value> getList() {
        return list;
    }
    
    @Override
    public String render(IConditionRenderer renderer) {
        return renderer.render(this);
    }
}
