package ch.sama.sql.query.helper;

import ch.sama.sql.query.base.IQuery;

public class Condition {
	public enum TYPE {
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
	
	public TYPE getType() {
		return type;
	}
	
	public Object getLHS() {
		return lhs;
	}
	
	public Object getRHS() {
		return rhs;
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