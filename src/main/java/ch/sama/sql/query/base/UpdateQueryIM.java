package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.ICondition;

import java.util.Map;

public class UpdateQueryIM implements IQuery {
    private IQueryRenderer renderer;
    private IQuery parent;
	
    private Map<Field, Value> values;

    public UpdateQueryIM(IQueryRenderer renderer, IQuery parent, Map<Field, Value> values) {
        this.renderer = renderer;
        this.parent = parent;
        this.values = values;
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
    
    public Map<Field, Value> getValues() {
        return values;
    }
	
    public UpdateQueryIM set(Field field, Value value) {
        this.values.put(field, value);
		
        return this;
    }
    
    public UpdateQueryIM set(String field, Value value) {
        return set(new Field(field), value);
    }
    
    public UpdateQueryFinal where(ICondition condition) {
        return new UpdateQueryFinal(renderer, this, condition);
    }
}