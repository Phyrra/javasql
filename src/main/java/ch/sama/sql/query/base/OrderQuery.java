package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.order.IOrder;

import java.util.ArrayList;
import java.util.List;

public class OrderQuery implements IQuery {
    private IQueryRenderer renderer;
	private IQuery parent;
	private List<IOrder> orders;

    @Override
	public IQuery getParent() {
		return parent;
	}
	
	public List<IOrder> getOrders() {
		return orders;
	}
	
	public OrderQuery(IQueryRenderer renderer, IQuery parent, IOrder... orders) {
        this.renderer = renderer;
		this.parent = parent;

        this.orders = new ArrayList<IOrder>();
        for (IOrder o : orders) {
            this.orders.add(o);
        }
	}

    @Override
    public String getSql() {
        return renderer.render(this);
    }
}