package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.helper.Value;

public class Query implements IQuery {
    private IQueryRenderer renderer;
    private IQuery parent;

	public Query(IQueryRenderer renderer) {
        this.renderer = renderer;
    }

    public Query(IQueryRenderer renderer, IQuery parent) {
        this.renderer = renderer;
        this.parent = parent;
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
		
	public SelectQuery select(Value... v) {
        return new SelectQuery(renderer, this, v);
	}

    public InsertQuery insert() {
        return new InsertQuery(renderer, this);
    }
    
    public DeleteQuery delete() {
        return new DeleteQuery(renderer, this);
    }
    
    public UpdateQuery update(Table table) {
        return new UpdateQuery(renderer, this, table);
    }

    public UpdateQuery update(String table) {
        return update(new Table(table));
    }

    public UnionQuery union(IQuery... queries) {
        return new UnionQuery(renderer, this, queries);
    }
}