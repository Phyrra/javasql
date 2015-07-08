package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.helper.Value;

import java.util.Arrays;
import java.util.List;

public class InsertQueryFinal implements IQuery {
    private IQueryRenderer renderer;
	private InsertQueryIM parent;
    private List<Field> fields;

    public InsertQueryFinal(IQueryRenderer renderer, InsertQueryIM parent, Field[] fields) {
        this.renderer = renderer;
        this.parent = parent;
        this.fields = Arrays.asList(fields);
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
    
    public List<Field> getFields() {
        return fields;
    }
    
    public Table getTable() {
        return parent.getTable();
    }
    
    public SelectQuery select(Value... values) {
        return new SelectQuery(renderer, this, values);
    }

    public InsertQueryValues values(Value... values) {
        return new InsertQueryValues(renderer, this, values);
    }
}