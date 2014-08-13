package ch.sama.sql.query.dialect;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.query.helper.Order;
import ch.sama.sql.query.helper.OrderParser;

public class SqlOrderParser implements OrderParser {
	public String parse(Order o) {
		StringBuilder builder = new StringBuilder();
		String prefix = "";
		
		builder.append("ORDER BY ");
		
		for (Field f : o.getFields()) {
			builder.append(prefix);
			builder.append(f.toString());
			
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
