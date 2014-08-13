import java.util.*;

public class FromQuery implements IQuery {
	private IQuery parent;
	private List<Table> tables;
	
	public String getSql() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(parent.getSql());
		
		String prefix = "";
		
		builder.append("\nFROM ");
		
		for (Table t : tables) {
			builder.append(prefix);
			builder.append(t.getName());
			
			prefix = ", ";
		}
		
		return builder.toString();
	}
	
	public FromQuery(IQuery parent, Table... t) {
		this.parent = parent;
		tables = new ArrayList<Table>();
		
		for (int i = 0; i < t.length; ++i) {
			tables.add(t[i]);
		}
	}
	
	public OrderQuery order(Order order) {
		return new OrderQuery(this, order);
	}
	
	public WhereQuery where(Condition condition) {
		return new WhereQuery(this, condition);
	}
}