package ch.sama.sql.query.helper;

import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.helper.condition.*;

public class Condition {
	public static EqCondition eq(Value lhs, Value rhs) {
        return new EqCondition(lhs, rhs);
	}
	
	public static NeqCondition neq(Value lhs, Value rhs) {
		return new NeqCondition(lhs, rhs);
	}
	
	public static LikeCondition like(Value lhs, Value rhs) {
		return new LikeCondition(lhs, rhs);
	}
	
	public static NotCondition not(ICondition condition) {
		return new NotCondition(condition);
	}
	
	public static AndCondition and(ICondition... conditions) {
        return new AndCondition(conditions);
	}
	
	public static OrCondition or(ICondition... conditions) {
        return new OrCondition(conditions);
	}

    public static GtCondition gt(Value lhs, Value rhs) {
        return new GtCondition(lhs, rhs);
    }

    public static GeCondition ge(Value lhs, Value rhs) {
        return new GeCondition(lhs, rhs);
    }

    public static LtCondition lt(Value lhs, Value rhs) {
        return new LtCondition(lhs, rhs);
    }

    public static LeCondition le(Value lhs, Value rhs) {
        return new LeCondition(lhs, rhs);
    }

    public static ExistsCondition exists(IQuery query) {
        return new ExistsCondition(query);
    }

    public static IsNullCondition isNull(Value value) {
        return new IsNullCondition(value);
    }

    public static InCondition in(Value value, IQuery query) {
        return new InCondition(value, query);
    }
}