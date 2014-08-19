package ch.sama.sql.dialect.tsql;

import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.ConditionParser;

public class TSqlConditionParser implements ConditionParser {
    @Override
    public String eq(Condition c) {
        return c.getLHS().toString() + " = " + c.getRHS().toString();
    }

    @Override
    public String neq(Condition c) {
        return c.getLHS().toString() + " <> " + c.getRHS().toString();
    }

    @Override
    public String like(Condition c) {
        return c.getLHS().toString() + " LIKE " + c.getRHS().toString();
    }

    @Override
    public String and(Condition c) {
        return "(" + ((Condition)c.getLHS()).toString(this) + " AND " + ((Condition)c.getRHS()).toString(this) + ")";
    }

    @Override
    public String or(Condition c) {
        return "(" + ((Condition)c.getLHS()).toString(this) + " OR " + ((Condition)c.getRHS()).toString(this) + ")";
    }

    @Override
    public String not(Condition c) {
        return "NOT (" + ((Condition)c.getLHS()).toString(this) + ")";
    }

    @Override
    public String gt(Condition c) {
        return c.getLHS().toString() + " > " + c.getRHS().toString();
    }

    @Override
    public String ge(Condition c) {
        return c.getLHS().toString() + " >= " + c.getRHS().toString();
    }

    @Override
    public String lt(Condition c) {
        return c.getLHS().toString() + " < " + c.getRHS().toString();
    }

    @Override
    public String le(Condition c) {
        return c.getLHS().toString() +" <= " + c.getRHS().toString();
    }

    @Override
    public String exists(Condition c) {
        return "EXISTS (\n" + c.getLHS().toString() + "\n)";
    }

    @Override
    public String isNull(Condition c) {
        return c.getLHS().toString() + " IS NULL";
    }

    @Override
    public String in(Condition c) {
        return c.getLHS().toString() + " IN (\n" + c.getRHS().toString() + "\n)";
    }
}
