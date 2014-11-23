package ch.sama.sql.query.base;

import java.util.*;

import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.ICondition;

public class SelectQuery implements IQuery {
    private IQueryRenderer renderer;
	private IQuery parent;
	private List<Value> values;	
	private int n;

    @Override
	public IQuery getParent() {
		return parent;
	}
	
	public List<Value> getValues() {
		return values;
	}
	
	public int getTopN() {
		return n;
	}
	
	public SelectQuery(IQueryRenderer renderer, IQuery parent, Value... v) {
        this.renderer = renderer;
		this.parent = parent;
		this.values = new ArrayList<Value>();
		this.n = -1;
		
		for (int i = 0; i < v.length; ++i) {
			values.add(v[i]);
		}
	}
	
	public SelectQuery top(int n) {
		this.n = n;
		return this;
	}
	
	public FromQuery from(Source... s) {
		return new FromQuery(renderer, this, s);
	}

    public Query union() {
        return new Query(renderer, this);
    }

    public WhereQuery where(ICondition c) {
        return new WhereQuery(renderer, this, c);
    }

    @Override
    public String getSql() {
        return renderer.render(this);
    }
}