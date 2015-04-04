package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.order.IOrder;

public class WhereQuery implements IQuery {
    private IQueryFactory factory;
	private IQuery parent;
	private ICondition condition;

    public WhereQuery(IQueryFactory factory, IQuery parent, ICondition condition) {
        this.factory = factory;
        this.parent = parent;
        this.condition = condition;
    }
    
    @Override
	public IQuery getParent() {
		return parent;
	}

    @Override
    public String getSql() {
        return factory.renderer().render(this);
    }

    @Override
    public IQuery chainTo(IQuery query) {
        this.parent = query;
        return query;
    }
    
	public ICondition getCondition() {
		return condition;
	}
	
	public OrderQuery order(IOrder... order) {
        return factory.order(this, order);
	}

    public Query union() {
        return factory.query(this);
    }
}