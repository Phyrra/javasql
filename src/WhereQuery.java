import java.util.*;

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