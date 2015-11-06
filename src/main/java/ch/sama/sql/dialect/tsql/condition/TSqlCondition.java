package ch.sama.sql.dialect.tsql.condition;

import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.CustomCondition;

import java.util.List;

public class TSqlCondition extends Condition {
    public static CustomCondition notNull(Value value) {
        return new NotNullCondition(value);
    }

    public static CustomCondition in(Value value, List<Value> list) {
        return new InListCondition(value, list);
    }

    public static CustomCondition between(Value start, Value stop) {
        return new BetweenCondition(start, stop);
    }
}
