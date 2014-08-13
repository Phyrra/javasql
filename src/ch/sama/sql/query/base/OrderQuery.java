package ch.sama.sql.query.base;

import ch.sama.sql.helper.Order;

public abstract class OrderQuery implements IQuery {
	private QueryFactory factory;
	private IQuery parent;
	private Order order;
	
	public IQuery getParent() {
		return parent;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public OrderQuery(QueryFactory factory, IQuery parent, Order order) {
		this.factory = factory;
		this.parent = parent;
		this.order = order;
	}
}