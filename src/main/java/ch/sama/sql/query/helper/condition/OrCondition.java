package ch.sama.sql.query.helper.condition;

import java.util.ArrayList;
import java.util.List;

public class OrCondition implements ICondition {
    private List<ICondition> conditions;

    public OrCondition(ICondition[] conditions) {
        this.conditions = new ArrayList<ICondition>();

        for (ICondition c : conditions) {
            this.conditions.add(c);
        }
    }

    public List<ICondition> getConditions() {
        return conditions;
    }
    
    @Override
    public String render(IConditionRenderer renderer) {
        return renderer.render(this);
    }
}
