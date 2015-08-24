package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.ICondition;

import java.util.Arrays;
import java.util.List;

public class SelectQuery implements IQuery {
    private IQueryRenderer renderer;
    private IQuery parent;
    private List<Value> values;

    public SelectQuery(IQueryRenderer renderer, IQuery parent, Value[] values) {
        this.renderer = renderer;
        this.parent = parent;
        this.values = Arrays.asList(values);
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

    public List<Value> getValues() {
        return values;
    }

    public FromQuery from(Source... sources) {
        return new FromQuery(renderer, this, sources);
    }

    public WhereQuery where(ICondition condition) {
        return new WhereQuery(renderer, this, condition);
    }
}