package ch.sama.sql.query.base;

import ch.sama.sql.query.base.checker.QueryFinder;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.query.helper.Identifier;
import ch.sama.sql.query.helper.Value;

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
        if (!Identifier.test(name)) {
            throw new IllegalIdentifierException(name);
        }

		this.factory = factory;
		this.parent = parent;
		this.name = name;
		this.query = null;		
	}
	
	public CTEQuery as(IQuery query) {
        if (new QueryFinder().get(query, CTEQuery.class) != null) {
            throw new BadSqlException("CTE cannot be nested");
        }

		this.query = query;
		return this;
	}

    private void assertQuery() {
        if (query == null) {
            throw new BadSqlException("Empty CTE");
        }
    }

    public CTEQuery with(String name) {
        assertQuery();

        return factory.cteQuery(factory, this, name);
    }
	
	public SelectQuery select(Value... v) {
		assertQuery();

        return factory.selectQuery(factory, this, v);
	}
}