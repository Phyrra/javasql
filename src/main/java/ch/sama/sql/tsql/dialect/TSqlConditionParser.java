package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.ConditionParser;
import ch.sama.sql.query.helper.Value;

import java.util.List;

public class TSqlConditionParser implements ConditionParser {
    private IQueryRenderer renderer;

    public TSqlConditionParser(IQueryRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public String eq(Value lhs, Value rhs) {
        return lhs.getValue() + " = " + rhs.getValue();
    }

    @Override
    public String neq(Value lhs, Value rhs) {
        return lhs.getValue() + " <> " + rhs.getValue();
    }

    @Override
    public String like(Value lhs, Value rhs) {
        return lhs.getValue() + " LIKE " + rhs.getValue();
    }

    private String join(List<Condition> conditions, String join) {
        StringBuilder builder = new StringBuilder();
        String prefix = "";

        builder.append("(");

        for (Condition c : conditions) {
            builder.append(prefix);
            builder.append(c.getString(this));

            prefix = join;
        }

        builder.append(")");

        return builder.toString();
    }

    @Override
    public String and(List<Condition> conditions) {
        return join(conditions, " AND ");
    }

    @Override
    public String or(List<Condition> conditions) {
        return join(conditions, " OR ");
    }

    @Override
    public String not(Condition c) {
        return "NOT (" + c.getString(this) + ")";
    }

    @Override
    public String gt(Value lhs, Value rhs) {
        return lhs.getValue() + " > " + rhs.getValue();
    }

    @Override
    public String ge(Value lhs, Value rhs) {
        return lhs.getValue() + " >= " + rhs.getValue();
    }

    @Override
    public String lt(Value lhs, Value rhs) {
        return lhs.getValue() + " < " + rhs.getValue();
    }

    @Override
    public String le(Value lhs, Value rhs) {
        return lhs.getValue() +" <= " + rhs.getValue();
    }

    @Override
    public String exists(IQuery query) {
        return "EXISTS (\n" + query.getSql(renderer) + "\n)";
    }

    @Override
    public String isNull(Value v) {
        return v.getValue() + " IS NULL";
    }

    @Override
    public String in(Value v, IQuery query) {
        return v.getValue() + " IN (\n" + query.getSql(renderer) + "\n)";
    }
}
