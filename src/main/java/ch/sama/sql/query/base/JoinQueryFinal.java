package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.order.IOrder;

public class JoinQueryFinal implements IQuery {
    private IQueryFactory factory;
	private JoinQuery parent;
	private ICondition condition;

	public JoinQueryFinal(IQueryFactory factory, JoinQuery parent, ICondition condition) {
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
        return this.parent.chainTo(query);
    }

	public Source getJoinSource() {
		return parent.getSource();
	}

	public ICondition getCondition() {
		return condition;
	}
	
	public JoinQuery join(Source source) {
		return factory.join(this, source);
	}
	
	public OrderQuery order(IOrder... order) {
		return factory.order(this, order);
	}
	
	public WhereQuery where(ICondition condition) {
		return factory.where(this, condition);
	}

    public Query union() {
		return factory.query(this);
    }
}