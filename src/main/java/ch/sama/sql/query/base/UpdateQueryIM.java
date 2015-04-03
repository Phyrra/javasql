package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.ICondition;

public class UpdateQueryIM implements IQuery {
    private IQueryRenderer renderer;
	private IQuery parent;
    private Field field;
    private Value value;

    public UpdateQueryIM(IQueryRenderer renderer, IQuery parent, Field field, Value value) {
        this.renderer = renderer;
        this.parent = parent;
        this.field = field;
        this.value = value;
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
    
    public Field getField() {
        return field;
    }
    
    public Value getValue() {
        return value;
    }
	
	public UpdateQueryIM set(Field field, Value value) {
		return new UpdateQueryIM(renderer, this, field, value);
	}
    
    public UpdateQueryIM set(String field, Value value) {
        return set(new Field(field), value);
    }
    
    public UpdateQueryFinal where(ICondition condition) {
        return new UpdateQueryFinal(renderer, this, condition);
    }
}