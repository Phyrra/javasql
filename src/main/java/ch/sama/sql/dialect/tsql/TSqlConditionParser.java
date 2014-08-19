package ch.sama.sql.dialect.tsql;

import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.ConditionParser;
import ch.sama.sql.query.helper.Value;

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

    @Override
    public String and(Condition lhs, Condition rhs) {
        return "(" + lhs.toString(this) + " AND " + rhs.toString(this) + ")";
    }

    @Override
    public String or(Condition lhs, Condition rhs) {
        return "(" + lhs.toString(this) + " OR " + rhs.toString(this) + ")";
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
