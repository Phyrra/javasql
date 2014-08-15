package ch.sama.sql.dialect.tsql;

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
		
		if (getAlias() != null) {
			builder.append(" AS ");
			builder.append(getAlias());
		}
		
		builder.append(" ON ");
		builder.append(getFactory().conditionParser().parse(getCondition()));
		
		return builder.toString();
	}
}
