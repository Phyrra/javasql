package ch.sama.sql.query.dialect;

import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.OrderQuery;
import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.query.helper.Order;

public class SqlOrderQuery extends OrderQuery {
	public SqlOrderQuery(QueryFactory factory, IQuery parent, Order order) {
		super(factory, parent, order);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(getParent().toString());		
		builder.append("\n");
		builder.append(getFactory().orderParser().parse(getOrder()));
		
		return builder.toString();
	}
}
