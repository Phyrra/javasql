package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.Value;

public abstract class Query implements IQuery {
	private QueryFactory factory;
    private IQuery parent;
	
	public Query(QueryFactory factory) {
		this.factory = factory;
	}

    public Query(QueryFactory factory, IQuery parent) {
        this.factory = factory;
        this.parent = parent;
    }
	
	public IQuery getParent() {
		return parent;
	}
	
	public QueryFactory getFactory() {
		return factory;
	}
	
	public CTEQuery with(String name) {
		return factory.cteQuery(factory, this, name);
	}
		
	public SelectQuery select(Value... v) {
		return factory.selectQuery(factory, this, v);
	}
}