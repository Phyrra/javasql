package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.helper.Value;

import java.util.LinkedHashMap;
import java.util.Map;

public class UpdateQuery implements IQuery {
    private IQueryRenderer renderer;
	private IQuery parent;
    private Table table;

    public UpdateQuery(IQueryRenderer renderer, IQuery parent, Table table) {
        this.renderer = renderer;
        this.parent = parent;
        this.table = table;
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
        this.parent = query;
        return query;
    }
    
    public Table getTable() {
        return table;
    }
	
    public UpdateQueryIM set(Map<Field, Value> values) {
        return new UpdateQueryIM(renderer, this, values);
    }

    public UpdateQueryIM set(Field field, Value value) {
        Map<Field, Value> values = new LinkedHashMap<Field, Value>();
        values.put(field, value);

        return set(values);
    }
    
    public UpdateQueryIM set(String field, Value value) {
        return set(new Field(field), value);
    }
}