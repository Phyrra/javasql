package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Table;

public class DeleteQuery implements IQuery {
    private IQueryRenderer renderer;
	private IQuery parent;

    @Override
	public IQuery getParent() {
		return parent;
	}

	public DeleteQuery(IQueryRenderer renderer, IQuery parent) {
        this.renderer = renderer;
		this.parent = parent;
	}
	
	public DeleteQueryIM from(Table table) {
		return new DeleteQueryIM(renderer, this, table);
	}
    
    public DeleteQueryIM from(String table) {
        return new DeleteQueryIM(renderer, this, new Table(table));
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
}