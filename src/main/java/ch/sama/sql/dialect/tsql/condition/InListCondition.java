package ch.sama.sql.dialect.tsql.condition;

import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.CustomCondition;
import ch.sama.sql.query.helper.condition.IConditionRenderer;

import java.util.List;
import java.util.stream.Collectors;

class InListCondition extends CustomCondition {
    private Value value;
    private List<Value> list;

    public InListCondition(Value value, List<Value> list) {
        super(() ->
                value.getValue() +
                " IN (\n" +
                list.stream()
                        .map(Value::getValue)
                        .collect(Collectors.joining(", ")) +
                "\n)"
        );

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
