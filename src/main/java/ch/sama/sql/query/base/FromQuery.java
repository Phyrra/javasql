package ch.sama.sql.query.base;

import java.util.*;

import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Order;
import ch.sama.sql.query.helper.Source;

public abstract class FromQuery implements IQuery {
	QueryFactory factory;
	private IQuery parent;
	private List<Source> sources;
	
	public IQuery getParent() {
		return parent;
	}
	
	public QueryFactory getFactory() {
		return factory;
	}
	
	public List<Source> getSources() {
		return sources;
	}
	
	public FromQuery(QueryFactory factory, IQuery parent, Source... s) {
		this.factory = factory;
		this.parent = parent;
		sources = new ArrayList<Source>();
		
		for (int i = 0; i < s.length; ++i) {
			sources.add(s[i]);
		}
	}
	
	public JoinQuery join(Source source) {
		return factory.joinQuery(factory, this, source);
	}
	
	public OrderQuery order(Order order) {
		return factory.orderQuery(factory, this, order);
	}
	
	public WhereQuery where(Condition condition) {
		return factory.whereQuery(factory, this, condition);
	}

    public Query union() {
        return factory.create(factory, this);
    }
}