package ch.sama.sql.query.helper;

import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.exception.UnknownOrderException;

import java.util.*;

public class Order {
	private enum TYPE {
		ASC,
		DESC
	}
	
	private TYPE type;
	private List<Value> values;
	
	private Order(TYPE type) {
		this.type = type;
	}

    public String getString(OrderParser parser) {
        switch (type) {
            case ASC:
                return parser.asc(values);
            case DESC:
                return parser.desc(values);
            default:
                throw new UnknownOrderException("Caused by: " + type);
        }
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
	
	private void fill(Value... vs) {
		values = new ArrayList<Value>();
		
		for (Value v : vs) {
            if (v.getAlias() != null) {
                throw new BadSqlException("Order values cannot have aliases");
            }

			values.add(v);
		}
	}
}