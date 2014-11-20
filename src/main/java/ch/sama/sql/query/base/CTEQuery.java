package ch.sama.sql.query.base;

import ch.sama.sql.query.base.checker.QueryFinder;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.query.base.checker.Identifier;
import ch.sama.sql.query.helper.Value;

public class CTEQuery implements IQuery {
	private IQuery parent;
	private String name;

    @Override
	public IQuery getParent() {
		return parent;
	}
	
	public String getName() {
		return name;
	}
	
	public CTEQuery(IQuery parent, String name) {
        if (!Identifier.test(name)) {
            throw new IllegalIdentifierException(name);
        }

		this.parent = parent;
		this.name = name;
	}
	
	public CTEQueryFinal as(IQuery query) {
        if (new QueryFinder().get(query, CTEQuery.class) != null) {
            throw new BadSqlException("CTE cannot be nested");
        }

		return new CTEQueryFinal(this, query);
	}

    @Override
    public String getSql(IQueryRenderer renderer) {
        return renderer.render(this);
    }
}