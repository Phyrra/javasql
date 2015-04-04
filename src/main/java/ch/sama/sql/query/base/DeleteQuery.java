package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Table;

public class DeleteQuery implements IQuery {
    private IQueryFactory factory;
	private IQuery parent;

    public DeleteQuery(IQueryFactory factory, IQuery parent) {
        this.factory = factory;
        this.parent = parent;
    }

    @Override
	public IQuery getParent() {
		return parent;
	}

    @Override
    public String getSql() {
        return factory.renderer().render(this);
    }

    @Override
    public IQuery chainTo(IQuery query) {
        this.parent = query;
        return query;
    }

	public DeleteQueryIM from(Table table) {
        return factory.deleteFrom(this, table);
	}
    
    public DeleteQueryIM from(String table) {
        return from(new Table(table));
    }
}