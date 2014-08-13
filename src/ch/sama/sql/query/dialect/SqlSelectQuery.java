package ch.sama.sql.query.dialect;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.query.base.SelectQuery;

public class SqlSelectQuery extends SelectQuery {
	public SqlSelectQuery(QueryFactory factory, IQuery parent, Field[] f) {
		super(factory, parent, f);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		String prefix = "";
		
		builder.append("SELECT ");
		
		for (Field f : getFields()) {
			builder.append(prefix);
			builder.append(f.toString());
			
			prefix = ", ";
		}	
		
		return builder.toString();
	}
}
