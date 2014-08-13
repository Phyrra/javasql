package ch.sama.sql.query.dialect;

import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.query.base.WhereQuery;
import ch.sama.sql.helper.Condition;

public class SqlWhereQuery extends WhereQuery {
	public SqlWhereQuery(QueryFactory factory, IQuery parent, Condition condition) {
		super(factory, parent, condition);
	}

	@Override
	public String getSql() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(getParent().getSql());
		builder.append("\nWHERE ");
		builder.append(getCondition().getString());
		
		return builder.toString();
	}
}
