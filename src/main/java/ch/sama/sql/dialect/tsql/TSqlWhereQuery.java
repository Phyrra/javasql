package ch.sama.sql.dialect.tsql;

import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.query.base.WhereQuery;
import ch.sama.sql.query.helper.Condition;

public class TSqlWhereQuery extends WhereQuery {
	public TSqlWhereQuery(QueryFactory factory, IQuery parent, Condition condition) {
		super(factory, parent, condition);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(getParent().toString());
		builder.append("\nWHERE ");
		builder.append(getCondition().toString(getFactory().conditionParser()));
		
		return builder.toString();
	}
}
