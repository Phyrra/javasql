package ch.sama.sql.query.helper;

import java.util.*;

public class Order {
	public enum TYPE {
		ASC,
		DESC
	}
	
	private TYPE type;
	private List<Value> values;
	
	private Order(TYPE type) {
		this.type = type;
	}
	
	public TYPE getType() {
		return type;
	}
	
	public List<Value> getValues() {
		return values;
	}
	
	public static Order asc(Value... v) {
		Order o = new Order(TYPE.ASC);
		o.fill(v);
		
		return o;
	}
	
	public static Order desc(Value... v) {
		Order o = new Order(TYPE.DESC);
		o.fill(v);
		
		return o;
	}
	
	private void fill(Value... v) {
		values = new ArrayList<Value>();
		
		for (int i = 0; i < v.length; ++i) {
			values.add(v[i]);
		}
	}
}