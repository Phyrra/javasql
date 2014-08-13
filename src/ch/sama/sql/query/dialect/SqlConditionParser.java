package ch.sama.sql.query.dialect;

import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.ConditionParser;
import ch.sama.sql.query.helper.Value;

public class SqlConditionParser implements ConditionParser {
	@Override
	public String parse(Condition c) {
		switch (c.getType()) {
			case EQ:
				return (String)c.getLHS() + " = " + c.getRHS().toString();
			case NEQ:
				return (String)c.getLHS() + " <> " + c.getRHS().toString();			
			case LIKE:
				return (String)c.getLHS() + " LIKE " + c.getRHS().toString();
			case AND:
				return "(" + parse((Condition)c.getLHS()) + " AND " + parse((Condition)c.getRHS()) + ")";
			case OR:
				return "(" + parse((Condition)c.getLHS()) + " OR " + parse((Condition)c.getRHS()) + ")";
			default:
				throw new RuntimeException("Unknown type: " + c.getType());
		}
	}
}
