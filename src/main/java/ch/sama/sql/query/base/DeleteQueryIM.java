package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.helper.condition.ICondition;

public class DeleteQueryIM implements IQuery {
    private IQueryFactory factory;
	private DeleteQuery parent;
    private Table table;

    public DeleteQueryIM(IQueryFactory factory, DeleteQuery parent, Table table) {
        this.factory = factory;
        this.parent = parent;
        this.table = table;
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
    
    public Table getTable() {
        return table;
    }
	
	public DeleteQueryFinal where(ICondition condition) {
        return factory.deleteWhere(this, condition);
	}
}