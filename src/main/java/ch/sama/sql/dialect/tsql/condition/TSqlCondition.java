package ch.sama.sql.dialect.tsql.condition;

import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.CustomCondition;

public class TSqlCondition extends Condition {
    public static CustomCondition notNull(Value value) {
        return new NotNullCondition(value);
    }

    public static CustomCondition between(Value start, Value stop) {
        return new BetweenCondition(start, stop);
    }

    public static ContainsTextCondition contains(Value lhs, String rhs) {
        return new ContainsTextCondition(lhs, rhs);
    }
}