package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.order.IOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FromQuery implements IQuery {
    private IQueryFactory factory;
	private IQuery parent;
	private List<Source> sources;

    public FromQuery(IQueryFactory factory, IQuery parent, Source[] sources) {
        this.factory = factory;
        this.parent = parent;
        this.sources = Arrays.asList(sources);
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
	
	public List<Source> getSources() {
		return sources;
	}
	
	public JoinQuery join(Source source) {
        return factory.join(this, source);
	}
	
	public OrderQuery order(IOrder... orders) {
        return factory.order(this, orders);
	}
	
	public WhereQuery where(ICondition condition) {
        return factory.where(this, condition);
	}

    public Query union() {
        return factory.query(this);
    }
}