package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.check.Identifier;
import ch.sama.sql.query.base.check.QueryFinder;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.exception.IllegalIdentifierException;

public class TSqlCteQuery implements IQuery {
    private TSqlQueryRenderer renderer;
	private IQuery parent;
	private String name;

    public TSqlCteQuery(TSqlQueryRenderer renderer, IQuery parent, String name) {
        if (!Identifier.test(name)) {
            throw new IllegalIdentifierException(name);
        }

        this.renderer = renderer;
        this.parent = parent;
        this.name = name;
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
    
	public String getName() {
		return name;
	}

	public TSqlCteQueryFinal as(IQuery query) {
        if (new QueryFinder().get(query, TSqlCteQuery.class) != null) {
            throw new BadSqlException("CTE cannot be nested");
        }

		return new TSqlCteQueryFinal(renderer, this, query);
	}
}