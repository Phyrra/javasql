package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.order.IOrder;

public class JoinQueryFinal implements IQuery {
    private IQueryRenderer renderer;
	private IQuery parent;
	private ICondition condition;

    @Override
	public IQuery getParent() {
		return parent;
	}

	public ICondition getCondition() {
		return condition;
	}

	public JoinQueryFinal(IQueryRenderer renderer, IQuery parent, ICondition condition) {
        this.renderer = renderer;
		this.parent = parent;
        this.condition = condition;
	}
	
	public JoinQuery join(Source source) {
        return new JoinQuery(renderer, this, source);
	}
	
	public OrderQuery order(IOrder... order) {
        return new OrderQuery(renderer, this, order);
	}
	
	public WhereQuery where(ICondition condition) {
        return new WhereQuery(renderer, this, condition);
	}

    public Query union() {
        return new Query(renderer, this);
    }

    @Override
    public String getSql() {
        return renderer.render(this);
    }
}