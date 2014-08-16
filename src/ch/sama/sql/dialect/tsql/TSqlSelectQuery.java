package ch.sama.sql.dialect.tsql;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.query.base.CTEQuery;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.query.base.SelectQuery;

public class TSqlSelectQuery extends SelectQuery {
	public TSqlSelectQuery(QueryFactory factory, IQuery parent, Field[] f) {
		super(factory, parent, f);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		IQuery parent = getParent();
		if (parent != null && parent instanceof CTEQuery) {
			builder.append(getParent().toString());
			builder.append("\n");
		}
		
		String prefix = "";
		
		builder.append("SELECT ");
		
		if (getTopN() > 0) {
			builder.append("TOP ");
			builder.append(getTopN());
			builder.append(" ");
		}
		
		for (Field f : getFields()) {
			builder.append(prefix);
			builder.append(f.toString());
			
			prefix = ", ";
		}	
		
		return builder.toString();
	}
}
