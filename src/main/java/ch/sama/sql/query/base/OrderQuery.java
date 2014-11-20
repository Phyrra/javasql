package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.Order;

public class OrderQuery implements IQuery {
	private IQuery parent;
	private Order order;

    @Override
	public IQuery getParent() {
		return parent;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public OrderQuery(IQuery parent, Order order) {
		this.parent = parent;
		this.order = order;
	}

    public OrderQuery order(Order order) {
        return new OrderQuery(this, order);
    }

    @Override
    public String getSql(IQueryRenderer renderer) {
        return renderer.render(this);
    }
}