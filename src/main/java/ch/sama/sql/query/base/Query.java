package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.Value;

public class Query implements IQuery {
    IQueryRenderer renderer;
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
	
	public CTEQuery with(String name) {
		return new CTEQuery(renderer, this, name);
	}
		
	public SelectQuery select(Value... v) {
		return new SelectQuery(renderer, this, v);
	}

    @Override
    public String getSql() {
        return renderer.render(this);
    }
}