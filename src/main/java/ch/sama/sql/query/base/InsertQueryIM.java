package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;

public class InsertQueryIM implements IQuery {
    private IQueryRenderer renderer;
	private IQuery parent;
    private Table table;

    @Override
	public IQuery getParent() {
		return parent;
	}

	public InsertQueryIM(IQueryRenderer renderer, IQuery parent, Table table) {
        this.renderer = renderer;
		this.parent = parent;
        this.table = table;
	}
	
	public InsertQueryFinal columns(Field... fields) {
		return new InsertQueryFinal(renderer, this, fields);
	}
    
    public InsertQueryFinal columns(String... fields) {
        Field[] fieldList = new Field[fields.length];
        for (int i = 0; i < fieldList.length; ++i) {
            fieldList[i] = new Field(fields[i]);
        }
        
        return new InsertQueryFinal(renderer, this, fieldList);
    }
    
    public Table getTable() {
        return table;
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