package ch.sama.sql.query;

import java.util.*;

import ch.sama.sql.helper.Order;

public class OrderQuery implements IQuery {
	private IQuery parent;
	private Order order;
	
	public String getSql() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(parent.getSql());		
		builder.append("\n");
		builder.append(order.getString());
		
		return builder.toString();
	}
	
	public OrderQuery(IQuery parent, Order order) {
		this.parent = parent;
		this.order = order;
	}
}