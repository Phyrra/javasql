package ch.sama.sql.tsql.dialect;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.JoinQuery;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.QueryFactory;

public class TSqlJoinQuery extends JoinQuery {
	public TSqlJoinQuery(QueryFactory factory, IQuery parent, Table table) {
		super(factory, parent, table);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(getParent().toString());
		
		builder.append("\n");
		
		if (getType() != null) {
			builder.append(getType());
			builder.append(" ");
		}
		builder.append("JOIN ");
		
		builder.append(getTable().toString());
		
		builder.append(" ON ");
		builder.append(getCondition().toString(getFactory().conditionParser()));
		
		return builder.toString();
	}
}
