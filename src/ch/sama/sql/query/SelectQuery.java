package ch.sama.sql.query;

import java.util.*;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;

public class SelectQuery implements IQuery {
	private IQuery parent;
	private List<Field> fields;
	
	public String getSql() {
		StringBuilder builder = new StringBuilder();
		String prefix = "";
		
		builder.append("SELECT ");
		
		for (Field f : fields) {
			builder.append(prefix);
			builder.append(f.getName());
			
			prefix = ", ";
		}	
		
		return builder.toString();
	}
	
	public SelectQuery(IQuery parent, Field... f) {
		this.parent = parent;
		fields = new ArrayList<Field>();
		
		for (int i = 0; i < f.length; ++i) {
			fields.add(f[i]);
		}
	}
	
	public FromQuery from(Table... t) {
		return new FromQuery(this, t);
	}
}