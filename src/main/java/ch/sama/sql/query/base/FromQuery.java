package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.order.IOrder;

import java.util.ArrayList;
import java.util.List;

public class FromQuery implements IQuery {
    private IQueryRenderer renderer;
	private IQuery parent;
	private List<Source> sources;

    public FromQuery(IQueryRenderer renderer, IQuery parent, Source... sources) {
        this.renderer = renderer;
        this.parent = parent;

        this.sources = new ArrayList<Source>();
        for (Source s : sources) {
            this.sources.add(s);
        }
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
	
	public List<Source> getSources() {
		return sources;
	}
	
	public JoinQuery join(Source source) {
		return new JoinQuery(renderer, this, source);
	}
	
	public OrderQuery order(IOrder... orders) {
		return new OrderQuery(renderer, this, orders);
	}
	
	public WhereQuery where(ICondition condition) {
		return new WhereQuery(renderer, this, condition);
	}

    public Query union() {
        return new Query(renderer, this);
    }
}