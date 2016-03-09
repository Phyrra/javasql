package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.order.IOrder;

import java.util.Arrays;
import java.util.List;

public class OrderQuery implements IQuery {
    private IQueryRenderer renderer;
	private IQuery parent;
	private List<IOrder> orders;

    public OrderQuery(IQueryRenderer renderer, IQuery parent, IOrder[] orders) {
        this.renderer = renderer;
        this.parent = parent;

        this.orders = Arrays.asList(orders);
    }
    
    @Override
	public IQuery getParent() {
		return parent;
	}

    @Override
    public String getSql() {
        return renderer.render(this);
    }

    @Override
    public IQuery chainTo(IQuery query) {
        this.parent = query;
        return query;
    }

    public List<IOrder> getOrders() {
        return orders;
    }
}