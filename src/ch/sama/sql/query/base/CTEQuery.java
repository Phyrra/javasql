package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Field;

public abstract class CTEQuery implements IQuery {
	QueryFactory factory;
	private IQuery parent;
	private String name;
	private IQuery query;
	
	public IQuery getParent() {
		return parent;
	}
	
	public QueryFactory getFactory() {
		return factory;
	}
	
	public String getName() {
		return name;
	}
	
	public IQuery getQuery() {
		return query;
	}
	
	public CTEQuery(QueryFactory factory, IQuery parent, String name) {
		this.factory = factory;
		this.parent = parent;
		this.name = name;
		this.query = null;		
	}
	
	public CTEQuery as(IQuery query) {
		this.query = query;
		return this;
	}
	
	public SelectQuery select(Field... f) {
		return factory.selectQuery(factory, this, f);
	}
}