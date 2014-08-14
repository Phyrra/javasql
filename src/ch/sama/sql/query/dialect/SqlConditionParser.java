package ch.sama.sql.query.dialect;

import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.ConditionParser;

public class SqlConditionParser implements ConditionParser {
	@Override
	public String parse(Condition c) {
		switch (c.getType()) {
			case EQ:
				return c.getLHS().toString() + " = " + c.getRHS().toString();
			case NEQ:
				return c.getLHS().toString() + " <> " + c.getRHS().toString();			
			case LIKE:
				return c.getLHS().toString() + " LIKE " + c.getRHS().toString();
			case AND:
				return "(" + parse((Condition)c.getLHS()) + " AND " + parse((Condition)c.getRHS()) + ")";
			case OR:
				return "(" + parse((Condition)c.getLHS()) + " OR " + parse((Condition)c.getRHS()) + ")";
			case NOT:
				return "NOT (" + parse((Condition)c.getLHS()) + ")";
			default:
				throw new RuntimeException("Unknown type: " + c.getType());
		}
	}
}
