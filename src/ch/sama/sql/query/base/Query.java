package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Field;

public abstract class Query implements IQuery {
	private QueryFactory factory;
	
	public Query(QueryFactory factory) {
		this.factory = factory;
	}
	
	public IQuery getParent() {
		return null;
	}
	
	public QueryFactory getFactory() {
		return factory;
	}
	
	public CTEQuery with(String name) {
		return factory.cteQuery(factory, this, name);
	}
		
	public SelectQuery select(Field... f) {
		return factory.selectQuery(factory, this, f);
	}
}