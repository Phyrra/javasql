package ch.sama.sql.query;

import java.util.*;

import ch.sama.sql.helper.Condition;
import ch.sama.sql.helper.Order;

public class WhereQuery implements IQuery {
	private IQuery parent;
	private Condition condition;
	
	public String getSql() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(parent.getSql());
		builder.append("\nWHERE ");
		builder.append(condition.getString());
		
		return builder.toString();
	}
	
	public WhereQuery(IQuery parent, Condition condition) {
		this.parent = parent;
		this.condition = condition;
	}
	
	public OrderQuery order(Order order) {
		return new OrderQuery(this, order);
	}
}