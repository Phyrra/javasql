package ch.sama.sql.query.base;

import java.util.*;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.helper.Value;

public abstract class SelectQuery implements IQuery {
	private QueryFactory factory;
	private IQuery parent;
	private List<Value> values;	
	private int n;
	
	public IQuery getParent() {
		return parent;
	}
	
	public QueryFactory getFactory() {
		return factory;
	}
	
	public List<Value> getValues() {
		return values;
	}
	
	public int getTopN() {
		return n;
	}
	
	public SelectQuery(QueryFactory factory, IQuery parent, Value... v) {
		this.factory = factory;
		this.parent = parent;
		this.values = new ArrayList<Value>();
		this.n = -1;
		
		for (int i = 0; i < v.length; ++i) {
			values.add(v[i]);
		}
	}
	
	public SelectQuery top(int n) {
		this.n = n;
		return this;
	}
	
	public FromQuery from(Table... t) {
		return factory.fromQuery(factory, this, t);
	}

    public Query union() {
        return factory.create(factory, this);
    }
}