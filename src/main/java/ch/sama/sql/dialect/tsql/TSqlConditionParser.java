package ch.sama.sql.dialect.tsql;

import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.ConditionParser;

public class TSqlConditionParser implements ConditionParser {
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
            case GT:
                return c.getLHS().toString() + " > " + c.getRHS().toString();
            case GE:
                return c.getLHS().toString() + " >= " + c.getRHS().toString();
            case LT:
                return c.getLHS().toString() + " < " + c.getRHS().toString();
            case LE:
                return c.getLHS().toString() +" <= " + c.getRHS().toString();
            case EXISTS:
                return "EXISTS (\n" + c.getLHS().toString() + "\n)";
            case NULL:
                return c.getLHS().toString() + " IS NULL";
            case IN:
                return c.getLHS().toString() + " IN (\n" + c.getRHS().toString() + "\n)";
			default:
				throw new RuntimeException("Unknown type: " + c.getType());
		}
	}
}
