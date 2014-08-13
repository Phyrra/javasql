package ch.sama.sql.query.dialect;

import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.OrderQuery;
import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.helper.Order;

public class SqlOrderQuery extends OrderQuery {
	public SqlOrderQuery(QueryFactory factory, IQuery parent, Order order) {
		super(factory, parent, order);
	}
	
	@Override
	public String getSql() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(getParent().getSql());		
		builder.append("\n");
		builder.append(getOrder().getString());
		
		return builder.toString();
	}
}
