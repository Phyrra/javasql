package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.condition.ICondition;

public class UpdateQueryFinal implements IQuery {
    private IQueryRenderer renderer;
	private IQuery parent;
    private ICondition condition;

    public UpdateQueryFinal(IQueryRenderer renderer, IQuery parent, ICondition condition) {
        this.renderer = renderer;
        this.parent = parent;
        this.condition = condition;
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
        return this.parent.chainTo(query);
    }
    
    public ICondition getCondition() {
        return condition;
    }
}