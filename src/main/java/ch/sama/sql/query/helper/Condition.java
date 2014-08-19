package ch.sama.sql.query.helper;

import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.exception.UnknownConditionException;

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
	private Object lhs;
	private Object rhs;
	
	private Condition(TYPE type) {
		this.type = type;
	}

    public String toString(ConditionParser parser) {
        switch (type) {
            case EQ:
                return parser.eq((Value)lhs, (Value)rhs);
            case NEQ:
                return parser.neq((Value)lhs, (Value)rhs);
            case LIKE:
                return parser.like((Value)lhs, (Value)rhs);
            case AND:
                return parser.and((Condition)lhs, (Condition)rhs);
            case OR:
                return parser.or((Condition)lhs, (Condition)rhs);
            case NOT:
                return parser.not((Condition)lhs);
            case GT:
                return parser.gt((Value)lhs, (Value)rhs);
            case GE:
                return parser.ge((Value)lhs, (Value)rhs);
            case LT:
                return parser.lt((Value)lhs, (Value)rhs);
            case LE:
                return parser.le((Value)lhs, (Value)rhs);
            case EXISTS:
                return parser.exists((IQuery)lhs);
            case NULL:
                return parser.isNull((Value)lhs);
            case IN:
                return parser.in((Value)lhs, (IQuery)rhs);
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
	
	public static Condition not(Condition lhs) {
		Condition c = new Condition(TYPE.NOT);
	
		c.lhs = lhs;
		
		return c;
	}
	
	public static Condition and(Condition lhs, Condition rhs) {
		Condition c = new Condition(TYPE.AND);
	
		c.lhs = lhs;
		c.rhs = rhs;
		
		return c;	
	}
	
	public static Condition or(Condition lhs, Condition rhs) {
		Condition c = new Condition(TYPE.OR);
	
		c.lhs = lhs;
		c.rhs = rhs;
		
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

    public static Condition exists(IQuery exists) {
        Condition c = new Condition(TYPE.EXISTS);

        c.lhs = exists;

        return c;
    }

    public static Condition isNull(Value lhs) {
        Condition c = new Condition(TYPE.NULL);

        c.lhs = lhs;

        return c;
    }

    public static Condition in(Value lhs, IQuery rhs) {
        Condition c = new Condition(TYPE.IN);

        c.lhs = lhs;
        c.rhs = rhs;

        return c;
    }
}