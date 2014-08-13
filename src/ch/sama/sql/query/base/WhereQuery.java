package ch.sama.sql.query.base;

import java.util.*;

import ch.sama.sql.helper.Condition;
import ch.sama.sql.helper.Order;

public abstract class WhereQuery implements IQuery {
	private QueryFactory factory;
	private IQuery parent;
	private Condition condition;
	
	public IQuery getParent() {
		return parent;
	}
	
	public Condition getCondition() {
		return condition;
	}
	
	public WhereQuery(QueryFactory factory, IQuery parent, Condition condition) {
		this.factory = factory;
		this.parent = parent;
		this.condition = condition;
	}
	
	public OrderQuery order(Order order) {
		return factory.orderQuery(factory, this, order);
	}
}