package ch.sama.sql.query.base;

import java.util.*;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;

public abstract class SelectQuery implements IQuery {
	private QueryFactory factory;
	private IQuery parent;
	private List<Field> fields;
	
	public IQuery getParent() {
		return parent;
	}
	
	public List<Field> getFields() {
		return fields;
	}
	
	public SelectQuery(QueryFactory factory, IQuery parent, Field... f) {
		this.factory = factory;
		this.parent = parent;
		fields = new ArrayList<Field>();
		
		for (int i = 0; i < f.length; ++i) {
			fields.add(f[i]);
		}
	}
	
	public FromQuery from(Table... t) {
		return factory.fromQuery(factory, this, t);
	}
}