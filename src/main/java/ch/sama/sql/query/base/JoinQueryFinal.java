package ch.sama.sql.query.base;

import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Order;
import ch.sama.sql.query.helper.Source;

public class JoinQueryFinal implements IQuery {
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

	public JoinQueryFinal(IQueryRenderer renderer, IQuery parent, Condition condition) {
        this.renderer = renderer;
		this.parent = parent;
        this.condition = condition;
	}
	
	public JoinQuery join(Source source) {
        return new JoinQuery(renderer, this, source);
	}
	
	public OrderQuery order(Order order) {
        return new OrderQuery(renderer, this, order);
	}
	
	public WhereQuery where(Condition condition) {
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