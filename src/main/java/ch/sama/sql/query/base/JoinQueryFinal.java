package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.order.IOrder;

public class JoinQueryFinal implements IQuery {
    private IQueryRenderer renderer;
	private JoinQuery parent;
	private ICondition condition;

	public JoinQueryFinal(IQueryRenderer renderer, JoinQuery parent, ICondition condition) {
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
        return this.parent.chainTo(query);
    }

	public Source getJoinSource() {
		return parent.getSource();
	}

	public ICondition getCondition() {
		return condition;
	}
	
	public JoinQuery join(Source source) {
		return new JoinQuery(renderer, this, source);
	}
	
	public OrderQuery order(IOrder... o) {
		return new OrderQuery(renderer, this, o);
	}
	
	public WhereQuery where(ICondition condition) {
		return new WhereQuery(renderer, this, condition);
	}
}