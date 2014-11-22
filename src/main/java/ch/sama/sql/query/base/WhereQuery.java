package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Order;

public class WhereQuery implements IQuery {
    IQueryRenderer renderer;
	private IQuery parent;
	private Condition condition;

    @Override
	public IQuery getParent() {
		return parent;
	}
	
	public Condition getCondition() {
		return condition;
	}
	
	public WhereQuery(IQueryRenderer renderer, IQuery parent, Condition condition) {
        this.renderer = renderer;
		this.parent = parent;
		this.condition = condition;
	}
	
	public OrderQuery order(Order order) {
		return new OrderQuery(renderer, this, order);
	}

    public Query union() {
        return new Query(renderer, this);
    }

    @Override
    public String getSql() {
        return renderer.render(this);
    }
}