package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.OrderQuery;
import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.query.helper.Order;

public class TSqlOrderQuery extends OrderQuery {
	public TSqlOrderQuery(QueryFactory factory, IQuery parent, Order order) {
		super(factory, parent, order);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

        IQuery parent = getParent();
        builder.append(parent.toString());

        if (parent instanceof OrderQuery) {
            builder.append(", ");
        } else {
            builder.append("\n");
            builder.append("ORDER BY ");
        }

		builder.append(getOrder().toString(getFactory().orderParser()));
		
		return builder.toString();
	}
}
