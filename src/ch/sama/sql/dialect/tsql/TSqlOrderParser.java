package ch.sama.sql.dialect.tsql;

import ch.sama.sql.query.helper.Order;
import ch.sama.sql.query.helper.OrderParser;
import ch.sama.sql.query.helper.Value;

public class TSqlOrderParser implements OrderParser {
	public String parse(Order o) {
		StringBuilder builder = new StringBuilder();
		String prefix = "";
		
		builder.append("ORDER BY ");
		
		for (Value v : o.getValues()) {
			builder.append(prefix);
			builder.append(v.toString());
			
			prefix = ", ";
		}
		
		switch (o.getType()) {
			case ASC:
				builder.append(" ASC");
				break;
			case DESC:
				builder.append(" DESC");
				break;
			default:
				throw new RuntimeException("Unknown Type: " + o.getType());
		}
		
		return builder.toString();
	}

}
