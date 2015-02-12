package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.helper.condition.ICondition;

public class DeleteQueryIM implements IQuery {
    private IQueryRenderer renderer;
	private IQuery parent;
    private Table table;

    @Override
	public IQuery getParent() {
		return parent;
	}

	public DeleteQueryIM(IQueryRenderer renderer, IQuery parent, Table table) {
        this.renderer = renderer;
		this.parent = parent;
        this.table = table;
	}
    
    public Table getTable() {
        return table;
    }
	
	public DeleteQueryFinal where(ICondition condition) {
		return new DeleteQueryFinal(renderer, this, condition);
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