package ch.sama.sql.dialect.tsql.condition;

import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.CustomCondition;
import ch.sama.sql.query.helper.condition.IConditionRenderer;

import java.util.List;

class InListCondition extends CustomCondition {
    private Value value;
    private List<Value> list;

    public InListCondition(Value value, List<Value> list) {
        super(() -> {
            StringBuilder builder = new StringBuilder();
            String prefix = "";

            for (Value val : list) {
                builder.append(prefix);
                builder.append(val.getValue());

                prefix = ", ";
            }

            return value.getValue() + " IN (\n" + builder.toString() + "\n)";
        });

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
