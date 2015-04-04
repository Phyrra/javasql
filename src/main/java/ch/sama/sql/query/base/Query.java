package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.helper.Value;

public class Query implements IQuery {
    private IQueryFactory factory;
    private IQuery parent;

	public Query(IQueryFactory factory) {
        this.factory = factory;
    }

    public Query(IQueryFactory factory, IQuery parent) {
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
		
	public SelectQuery select(Value... v) {
        return factory.select(this, v);
	}

    public InsertQuery insert() {
        return factory.insert(this);
    }
    
    public DeleteQuery delete() {
        return factory.delete(this);
    }
    
    public UpdateQuery update(Table table) {
        return factory.update(this, table);
    }

    public UpdateQuery update(String table) {
        return update(new Table(table));
    }
}