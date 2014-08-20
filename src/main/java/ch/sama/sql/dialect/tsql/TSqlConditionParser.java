package ch.sama.sql.dialect.tsql;

import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.ConditionParser;
import ch.sama.sql.query.helper.Value;

import java.util.List;

public class TSqlConditionParser implements ConditionParser {
    @Override
    public String eq(Value lhs, Value rhs) {
        return lhs.toString() + " = " + rhs.toString();
    }

    @Override
    public String neq(Value lhs, Value rhs) {
        return lhs.toString() + " <> " + rhs.toString();
    }

    @Override
    public String like(Value lhs, Value rhs) {
        return lhs.toString() + " LIKE " + rhs.toString();
    }

    private String join(List<Condition> conditions, String join) {
        StringBuilder builder = new StringBuilder();
        String prefix = "";

        builder.append("(");

        for (Condition c : conditions) {
            builder.append(prefix);
            builder.append(c.toString(this));

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
        return "NOT (" + c.toString(this) + ")";
    }

    @Override
    public String gt(Value lhs, Value rhs) {
        return lhs.toString() + " > " + rhs.toString();
    }

    @Override
    public String ge(Value lhs, Value rhs) {
        return lhs.toString() + " >= " + rhs.toString();
    }

    @Override
    public String lt(Value lhs, Value rhs) {
        return lhs.toString() + " < " + rhs.toString();
    }

    @Override
    public String le(Value lhs, Value rhs) {
        return lhs.toString() +" <= " + rhs.toString();
    }

    @Override
    public String exists(IQuery query) {
        return "EXISTS (\n" + query.toString() + "\n)";
    }

    @Override
    public String isNull(Value v) {
        return v.toString() + " IS NULL";
    }

    @Override
    public String in(Value v, IQuery query) {
        return v.toString() + " IN (\n" + query.toString() + "\n)";
    }
}
