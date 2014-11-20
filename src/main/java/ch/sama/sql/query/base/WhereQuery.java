package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Order;

public class WhereQuery implements IQuery {
	private IQuery parent;
	private Condition condition;

    @Override
	public IQuery getParent() {
		return parent;
	}
	
	public Condition getCondition() {
		return condition;
	}
	
	public WhereQuery(IQuery parent, Condition condition) {
		this.parent = parent;
		this.condition = condition;
	}
	
	public OrderQuery order(Order order) {
		return new OrderQuery(this, order);
	}

    public Query union() {
        return new Query(this);
    }

    @Override
    public String getSql(IQueryRenderer renderer) {
        return renderer.render(this);
    }
}