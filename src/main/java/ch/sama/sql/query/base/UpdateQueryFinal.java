package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.condition.ICondition;

public class UpdateQueryFinal implements IQuery {
    private IQueryFactory factory;
	private IQuery parent;
    private ICondition condition;

    public UpdateQueryFinal(IQueryFactory factory, IQuery parent, ICondition condition) {
        this.factory = factory;
        this.parent = parent;
        this.condition = condition;
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
        return this.parent.chainTo(query);
    }
    
    public ICondition getCondition() {
        return condition;
    }
}