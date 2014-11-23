package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.order.IOrder;

public class OrderQuery implements IQuery {
    private IQueryRenderer renderer;
	private IQuery parent;
	private IOrder order;

    @Override
	public IQuery getParent() {
		return parent;
	}
	
	public IOrder getOrder() {
		return order;
	}
	
	public OrderQuery(IQueryRenderer renderer, IQuery parent, IOrder order) {
        this.renderer = renderer;
		this.parent = parent;
		this.order = order;
	}

    public OrderQuery order(IOrder order) {
        return new OrderQuery(renderer, this, order);
    }

    @Override
    public String getSql() {
        return renderer.render(this);
    }
}