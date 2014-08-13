package ch.sama.sql.query.dialect;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.FromQuery;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.QueryFactory;

public class SqlFromQuery extends FromQuery {
	public SqlFromQuery(QueryFactory factory, IQuery parent, Table[] t) {
		super(factory, parent, t);
	}
	
	@Override
	public String getSql() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(getParent().getSql());
		
		String prefix = "";
		
		builder.append("\nFROM ");
		
		for (Table t : getTables()) {
			builder.append(prefix);
			builder.append(t.getName());
			
			prefix = ", ";
		}
		
		return builder.toString();
	}
}
