package ch.sama.sql.query.generic;

import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.*;

import java.util.List;

public abstract class ConditionRenderer implements IConditionRenderer {
    @Override
    public String render(EqCondition c) {
        return c.getLhs().getValue() + " = " + c.getRhs().getValue();
    }

    @Override
    public String render(NeqCondition c) {
        return c.getLhs().getValue() + " <> " + c.getRhs().getValue();
    }

    @Override
    public String render(LikeCondition c) {
        return c.getLhs().getValue() + " LIKE " + c.getRhs().getValue();
    }

    private String join(List<ICondition> conditions, String join) {
        StringBuilder builder = new StringBuilder();
        String prefix = "";

        builder.append("(");

        for (ICondition c : conditions) {
            builder.append(prefix);
            builder.append(c.render(this));

            prefix = join;
        }

        builder.append(")");

        return builder.toString();
    }

    @Override
    public String render(AndCondition c) {
        return join(c.getConditions(), " AND ");
    }

    @Override
    public String render(OrCondition c) {
        return join(c.getConditions(), " OR ");
    }

    @Override
    public String render(NotCondition c) {
        return "NOT (" + c.getCondition().render(this) + ")";
    }

    @Override
    public String render(GtCondition c) {
        return c.getLhs().getValue() + " > " + c.getRhs().getValue();
    }

    @Override
    public String render(GeCondition c) {
        return c.getLhs().getValue() + " >= " + c.getRhs().getValue();
    }

    @Override
    public String render(LtCondition c) {
        return c.getLhs().getValue() + " < " + c.getRhs().getValue();
    }

    @Override
    public String render(LeCondition c) {
        return c.getLhs().getValue() +" <= " + c.getRhs().getValue();
    }

    @Override
    public String render(ExistsCondition c) {
        return "EXISTS (\n" + c.getQuery().getSql() + "\n)";
    }

    @Override
    public String render(IsNullCondition c) {
        return c.getValue().getValue() + " IS NULL";
    }

    @Override
    public String render(InQueryCondition c) {
        return c.getValue().getValue() + " IN (\n" + c.getQuery().getSql() + "\n)";
    }
    
    @Override
    public String render(InListCondition c) {
        StringBuilder builder = new StringBuilder();
        String prefix = "";

        for (Value value : c.getList()) {
            builder.append(prefix);
            builder.append(value.getValue());

            prefix = ", ";
        }
        
        return c.getValue().getValue() + " IN (\n" + builder.toString() + "\n)";
    }
}
