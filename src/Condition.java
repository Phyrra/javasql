public class Condition {
	private enum TYPE {
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
	
	public String getString() {
		switch (type) {
			case EQ:
				return (String)lhs + " = " + ((Value)rhs).getString();
			case NEQ:
				return (String)lhs + " <> " + ((Value)rhs).getString();			
			case LIKE:
				return (String)lhs + " LIKE " + ((Value)rhs).getString();
			case AND:
				return "(" + ((Condition)lhs).getString() + " AND " + ((Condition)rhs).getString() + ")";
			case OR:
				return "(" + ((Condition)lhs).getString() + " OR " + ((Condition)rhs).getString() + ")";
			default:
				throw new RuntimeException("Unknown type: " + type);
		}
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