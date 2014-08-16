package ch.sama.sql.query.base;

import java.util.*;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;

public abstract class SelectQuery implements IQuery {
	private QueryFactory factory;
	private IQuery parent;
	private List<Field> fields;
	private int n;
	
	public IQuery getParent() {
		return parent;
	}
	
	public QueryFactory getFactory() {
		return factory;
	}
	
	public List<Field> getFields() {
		return fields;
	}
	
	public int getTopN() {
		return n;
	}
	
	public SelectQuery(QueryFactory factory, IQuery parent, Field... f) {
		this.factory = factory;
		this.parent = parent;
		this.fields = new ArrayList<Field>();
		this.n = -1;
		
		for (int i = 0; i < f.length; ++i) {
			fields.add(f[i]);
		}
	}
	
	public SelectQuery top(int n) {
		this.n = n;
		return this;
	}
	
	public FromQuery from(Table... t) {
		return factory.fromQuery(factory, this, t);
	}
}