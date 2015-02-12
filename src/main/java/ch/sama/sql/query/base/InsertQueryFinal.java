package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.query.helper.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InsertQueryFinal implements IQuery {
    private IQueryRenderer renderer;
	private IQuery parent;
    private List<Field> fields;

    @Override
	public IQuery getParent() {
		return parent;
	}

	public InsertQueryFinal(IQueryRenderer renderer, IQuery parent, Field... fields) {
        this.renderer = renderer;
		this.parent = parent;
        
        this.fields = new ArrayList<Field>();
        Collections.addAll(this.fields, fields);
	}
    
    public List<Field> getFields() {
        return fields;
    }
    
    public SelectQuery select(Value... values) {
        return new SelectQuery(renderer, this, values);
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