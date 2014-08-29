package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.base.CTEQuery;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.query.base.SelectQuery;
import ch.sama.sql.query.helper.Value;

public class TSqlSelectQuery extends SelectQuery {
	public TSqlSelectQuery(QueryFactory factory, IQuery parent, Value[] v) {
		super(factory, parent, v);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		IQuery parent = getParent();
		if (parent != null) {
            String parentQuery = parent.toString();
            if (parentQuery != null) {
                builder.append(parentQuery);
                builder.append("\n");
            }
		}
		
		String prefix = "";
		
		builder.append("SELECT ");
		
		if (getTopN() > 0) {
			builder.append("TOP ");
			builder.append(getTopN());
			builder.append(" ");
		}
		
		for (Value v : getValues()) {
			builder.append(prefix);
			builder.append(v.toString());
			
			prefix = ", ";
		}	
		
		return builder.toString();
	}
}
