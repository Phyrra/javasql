package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.condition.ICondition;

public class DeleteQueryFinal implements IQuery {
    private IQueryRenderer renderer;
	private IQuery parent;
    private ICondition condition;

    @Override
	public IQuery getParent() {
		return parent;
	}

	public DeleteQueryFinal(IQueryRenderer renderer, IQuery parent, ICondition condition) {
        this.renderer = renderer;
		this.parent = parent;
        this.condition = condition;
	}
    
    public ICondition getCondition() {
        return condition;
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