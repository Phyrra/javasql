package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.helper.Condition;
import ch.sama.sql.helper.Order;

public interface QueryFactory {
	public SelectQuery selectQuery(QueryFactory factory, IQuery parent, Field... f);
	public FromQuery fromQuery(QueryFactory factory, IQuery parent, Table... t);
	public WhereQuery whereQuery(QueryFactory factory, IQuery parent, Condition condition);
	public OrderQuery orderQuery(QueryFactory factory, IQuery parent, Order order);
}
