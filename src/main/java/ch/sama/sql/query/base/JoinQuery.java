package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Order;

public abstract class JoinQuery implements IQuery {
	QueryFactory factory;
	private IQuery parent;
	private Table table;
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
	
	public JoinQuery on(Condition condition) {
		this.condition = condition;
		return this;
	}

    private void assertCondition() {
        if (condition == null) {
            throw new BadSqlException("Missing join condition");
        }
    }
	
	public JoinQuery join(Table table) {
        assertCondition();

        return factory.joinQuery(factory, this, table);
	}
	
	public OrderQuery order(Order order) {
        assertCondition();

        return factory.orderQuery(factory, this, order);
	}
	
	public WhereQuery where(Condition condition) {
        assertCondition();

        return factory.whereQuery(factory, this, condition);
	}

    public Query union() {
        assertCondition();

        return factory.create(factory, this);
    }
}