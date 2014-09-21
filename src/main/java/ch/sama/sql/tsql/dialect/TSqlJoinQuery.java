package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.base.JoinQuery;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.query.helper.Source;

public class TSqlJoinQuery extends JoinQuery {
	public TSqlJoinQuery(QueryFactory factory, IQuery parent, Source source) {
		super(factory, parent, source);
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
		
		builder.append(getSource().toString());
		
		builder.append(" ON ");
		builder.append(getCondition().toString(getFactory().conditionParser()));
		
		return builder.toString();
	}
}
