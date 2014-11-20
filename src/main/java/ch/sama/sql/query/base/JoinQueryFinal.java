package ch.sama.sql.query.base;

import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Order;
import ch.sama.sql.query.helper.Source;

public class JoinQueryFinal implements IQuery {
	private IQuery parent;
	private Condition condition;

    @Override
	public IQuery getParent() {
		return parent;
	}

	public Condition getCondition() {
		return condition;
	}

	public JoinQueryFinal(IQuery parent, Condition condition) {
		this.parent = parent;
        this.condition = condition;
	}
	
	public JoinQuery join(Source source) {
        return new JoinQuery(this, source);
	}
	
	public OrderQuery order(Order order) {
        return new OrderQuery(this, order);
	}
	
	public WhereQuery where(Condition condition) {
        return new WhereQuery(this, condition);
	}

    public Query union() {
        return new Query(this);
    }

    @Override
    public String getSql(IQueryRenderer renderer) {
        return renderer.render(this);
    }
}