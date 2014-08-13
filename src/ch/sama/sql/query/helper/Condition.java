package ch.sama.sql.query.helper;

import ch.sama.sql.query.base.QueryFactory;

public class Condition {
	public enum TYPE {
		AND,
		OR,
		EQ,
		NEQ,
		NOT,
		LIKE
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
	
	public static Condition eq(String field, Value value) {
		Condition c = new Condition(TYPE.EQ);
	
		c.lhs = field;
		c.rhs = value;
		
		return c;
	}
	
	public static Condition neq(String field, Value value) {
		Condition c = new Condition(TYPE.NEQ);
	
		c.lhs = field;
		c.rhs = value;
		
		return c;
	}
	
	public static Condition like(String field, Value value) {
		Condition c = new Condition(TYPE.LIKE);
	
		c.lhs = field;
		c.rhs = value;
		
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
}