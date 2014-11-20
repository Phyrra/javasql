package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.Value;

public class Query implements IQuery {
    private IQuery parent;
	
	public Query() { }

    public Query(IQuery parent) {
        this.parent = parent;
    }

    @Override
	public IQuery getParent() {
		return parent;
	}
	
	public CTEQuery with(String name) {
		return new CTEQuery(this, name);
	}
		
	public SelectQuery select(Value... v) {
		return new SelectQuery(this, v);
	}

    @Override
    public String getSql(IQueryRenderer renderer) {
        return renderer.render(this);
    }
}