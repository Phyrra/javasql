package ch.sama.sql.query.helper;

import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.exception.BadParameterException;
import ch.sama.sql.query.exception.UnknownConditionException;

import java.util.ArrayList;
import java.util.List;

public class Condition {
	private enum TYPE {
		AND,
		OR,
		EQ,
		NEQ,
		NOT,
		LIKE,
        GT,
        GE,
        LT,
        LE,
        NULL,
        EXISTS,
        IN
	}
	
	private TYPE type;

	private Value lhs;
	private Value rhs;
    private Value val;

    private Condition condition;
    private List<Condition> conditions;

    private IQuery query;
	
	private Condition(TYPE type) {
		this.type = type;
	}

    public String getString(IConditionParser parser) {
        switch (type) {
            case EQ:
                return parser.eq(lhs, rhs);
            case NEQ:
                return parser.neq(lhs, rhs);
            case LIKE:
                return parser.like(lhs, rhs);
            case AND:
                return parser.and(conditions);
            case OR:
                return parser.or(conditions);
            case NOT:
                return parser.not(condition);
            case GT:
                return parser.gt(lhs, rhs);
            case GE:
                return parser.ge(lhs, rhs);
            case LT:
                return parser.lt(lhs, rhs);
            case LE:
                return parser.le(lhs, rhs);
            case EXISTS:
                return parser.exists(query);
            case NULL:
                return parser.isNull(val);
            case IN:
                return parser.in(val, query);
            default:
                throw new UnknownConditionException("Caused by: " + type);
        }
    }
	
	public static Condition eq(Value lhs, Value rhs) {
		Condition c = new Condition(TYPE.EQ);
	
		c.lhs = lhs;
		c.rhs = rhs;
		
		return c;
	}
	
	public static Condition neq(Value lhs, Value rhs) {
		Condition c = new Condition(TYPE.NEQ);
	
		c.lhs = lhs;
		c.rhs = rhs;
		
		return c;
	}
	
	public static Condition like(Value lhs, Value rhs) {
		Condition c = new Condition(TYPE.LIKE);
	
		c.lhs = lhs;
		c.rhs = rhs;
		
		return c;
	}
	
	public static Condition not(Condition condition) {
		Condition c = new Condition(TYPE.NOT);
	
		c.condition = condition;
		
		return c;
	}
	
	public static Condition and(Condition... conditions) {
        if (conditions.length <= 1) {
            throw new BadParameterException("Condition List too short");
        }

		Condition c = new Condition(TYPE.AND);

        c.conditions = new ArrayList<Condition>();
        for (int i = 0; i < conditions.length; ++i) {
            c.conditions.add(conditions[i]);
        }
		
		return c;	
	}
	
	public static Condition or(Condition... conditions) {
        if (conditions.length <= 1) {
            throw new BadParameterException("Condition List too short");
        }

        Condition c = new Condition(TYPE.OR);

        c.conditions = new ArrayList<Condition>();
        for (int i = 0; i < conditions.length; ++i) {
            c.conditions.add(conditions[i]);
        }
		
		return c;	
	}

    public static Condition gt(Value lhs, Value rhs) {
        Condition c = new Condition(TYPE.GT);

        c.lhs = lhs;
        c.rhs = rhs;

        return c;
    }

    public static Condition ge(Value lhs, Value rhs) {
        Condition c = new Condition(TYPE.GE);

        c.lhs = lhs;
        c.rhs = rhs;

        return c;
    }

    public static Condition lt(Value lhs, Value rhs) {
        Condition c = new Condition(TYPE.LT);

        c.lhs = lhs;
        c.rhs = rhs;

        return c;
    }

    public static Condition le(Value lhs, Value rhs) {
        Condition c = new Condition(TYPE.LE);

        c.lhs = lhs;
        c.rhs = rhs;

        return c;
    }

    public static Condition exists(IQuery query) {
        Condition c = new Condition(TYPE.EXISTS);

        c.query = query;

        return c;
    }

    public static Condition isNull(Value val) {
        Condition c = new Condition(TYPE.NULL);

        c.val = val;

        return c;
    }

    public static Condition in(Value val, IQuery query) {
        Condition c = new Condition(TYPE.IN);

        c.val = val;
        c.query = query;

        return c;
    }
}