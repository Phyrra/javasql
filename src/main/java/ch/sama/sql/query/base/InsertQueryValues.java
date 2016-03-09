package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.Value;

import java.util.Arrays;
import java.util.List;

public class InsertQueryValues implements IQuery {
    private IQueryRenderer renderer;
    private InsertQueryFinal parent;
    private List<Value> values;

    public InsertQueryValues(IQueryRenderer renderer, InsertQueryFinal parent, Value[] values) {
        this.renderer = renderer;
        this.parent = parent;
        this.values = Arrays.asList(values);
    }

    public List<Value> getValues() {
        return values;
    }

    @Override
    public InsertQueryFinal getParent() {
        return parent;
    }

    @Override
    public String getSql() {
        return renderer.render(this);
    }

    @Override
    public IQuery chainTo(IQuery query) {
        return parent.chainTo(query);
    }
}
