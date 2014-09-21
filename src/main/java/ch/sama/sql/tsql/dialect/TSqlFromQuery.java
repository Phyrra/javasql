package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.base.FromQuery;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.query.helper.Source;

public class TSqlFromQuery extends FromQuery {
	public TSqlFromQuery(QueryFactory factory, IQuery parent, Source[] s) {
		super(factory, parent, s);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(getParent().toString());
		
		String prefix = "";
		
		builder.append("\nFROM ");
		
		for (Source s : getSources()) {
			builder.append(prefix);
			builder.append(s.toString());
			
			prefix = ", ";
		}
		
		return builder.toString();
	}
}
