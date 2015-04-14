package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.order.IOrder;

public class WhereQuery implements IQuery {
    private IQueryRenderer renderer;
	private IQuery parent;
	private ICondition condition;

    public WhereQuery(IQueryRenderer renderer, IQuery parent, ICondition condition) {
        this.renderer = renderer;
        this.parent = parent;
        this.condition = condition;
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
    
	public ICondition getCondition() {
		return condition;
	}
	
	public OrderQuery order(IOrder... o) {
        return new OrderQuery(renderer, this, o);
	}

    public Query union() {
        return new Query(renderer, this);
    }
}