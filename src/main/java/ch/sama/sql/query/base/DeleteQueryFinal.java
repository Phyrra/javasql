package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.helper.condition.ICondition;

public class DeleteQueryFinal implements IQuery {
    private IQueryFactory factory;
	private DeleteQueryIM parent;
    private ICondition condition;

    public DeleteQueryFinal(IQueryFactory factory, DeleteQueryIM parent, ICondition condition) {
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
    
    public Table getTable() {
        return parent.getTable();
    }
}