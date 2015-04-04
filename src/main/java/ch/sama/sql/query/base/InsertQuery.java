package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Table;

public class InsertQuery implements IQuery {
    private IQueryFactory factory;
	private IQuery parent;

    public InsertQuery(IQueryFactory factory, IQuery parent) {
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
	
	public InsertQueryIM into(Table table) {
        return factory.insertInto(this, table);
	}
    
    public InsertQueryIM into(String table) {
        return into(new Table(table));
    }
}