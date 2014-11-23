package ch.sama.sql.query.base;

import java.util.*;

import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.order.IOrder;

public class FromQuery implements IQuery {
    private IQueryRenderer renderer;
	private IQuery parent;
	private List<Source> sources;

    @Override
	public IQuery getParent() {
		return parent;
	}
	
	public List<Source> getSources() {
		return sources;
	}
	
	public FromQuery(IQueryRenderer renderer, IQuery parent, Source... s) {
        this.renderer = renderer;
		this.parent = parent;
		sources = new ArrayList<Source>();
		
		for (int i = 0; i < s.length; ++i) {
			sources.add(s[i]);
		}
	}
	
	public JoinQuery join(Source source) {
		return new JoinQuery(renderer, this, source);
	}
	
	public OrderQuery order(IOrder order) {
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