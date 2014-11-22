package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.Order;

public class OrderQuery implements IQuery {
    IQueryRenderer renderer;
	private IQuery parent;
	private Order order;

    @Override
	public IQuery getParent() {
		return parent;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public OrderQuery(IQueryRenderer renderer, IQuery parent, Order order) {
        this.renderer = renderer;
		this.parent = parent;
		this.order = order;
	}

    public OrderQuery order(Order order) {
        return new OrderQuery(renderer, this, order);
    }

    @Override
    public String getSql() {
        return renderer.render(this);
    }
}