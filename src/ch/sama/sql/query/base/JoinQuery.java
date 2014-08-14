package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Order;

public abstract class JoinQuery implements IQuery {
	QueryFactory factory;
	private IQuery parent;
	private Table table;
	private String alias;
	private Condition condition;
	private String type;
	
	public IQuery getParent() {
		return parent;
	}
	
	public QueryFactory getFactory() {
		return factory;
	}
	
	public Table getTable() {
		return table;
	}
	
	public String getAlias() {
		return alias;
	}
	
	public Condition getCondition() {
		return condition;
	}
	
	public String getType() {
		return type;
	}
	
	public JoinQuery(QueryFactory factory, IQuery parent, Table table) {
		this.factory = factory;
		this.parent = parent;
		this.table = table;
		this.alias = null;
		this.type = null;
	}
	
	public JoinQuery left() {
		this.type = "LEFT";
		return this;
	}
	
	public JoinQuery right() {
		this.type = "RIGHT";
		return this;
	}
	
	// Could also add inner/outer/cross and so on..
	//	Since I never use them, I didn't :>
	
	public JoinQuery as(String alias) {
		this.alias = alias;
		return this;
	}
	
	public JoinQuery on(Condition condition) {
		this.condition = condition;
		return this;
	}
	
	public JoinQuery join(Table table) {
		return factory.joinQuery(factory, this, table);
	}
	
	public OrderQuery order(Order order) {
		return factory.orderQuery(factory, this, order);
	}
	
	public WhereQuery where(Condition condition) {
		return factory.whereQuery(factory, this, condition);
	}
}