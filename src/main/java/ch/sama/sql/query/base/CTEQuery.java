package ch.sama.sql.query.base;

import ch.sama.sql.query.base.check.QueryFinder;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.query.base.check.Identifier;

public class CTEQuery implements IQuery {
    private IQueryRenderer renderer;
	private IQuery parent;
	private String name;

    @Override
	public IQuery getParent() {
		return parent;
	}
	
	public String getName() {
		return name;
	}
	
	public CTEQuery(IQueryRenderer renderer, IQuery parent, String name) {
        if (!Identifier.test(name)) {
            throw new IllegalIdentifierException(name);
        }

        this.renderer = renderer;
		this.parent = parent;
		this.name = name;
	}
	
	public CTEQueryFinal as(IQuery query) {
        if (new QueryFinder().get(query, CTEQuery.class) != null) {
            throw new BadSqlException("CTE cannot be nested");
        }

		return new CTEQueryFinal(renderer, this, query);
	}

    @Override
    public String getSql() {
        return renderer.render(this);
    }
}