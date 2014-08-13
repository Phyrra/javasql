import java.util.*;

public class Order {
	private enum TYPE {
		ASC,
		DESC
	}
	
	private TYPE type;
	private List<Field> fields;
	
	private Order(TYPE type) {
		this.type = type;
	}
	
	public static Order asc(Field... f) {
		Order o = new Order(TYPE.ASC);
		o.fill(f);
		
		return o;
	}
	
	public static Order desc(Field... f) {
		Order o = new Order(TYPE.DESC);
		o.fill(f);
		
		return o;
	}
	
	private void fill(Field... f) {
		fields = new ArrayList<Field>();
		
		for (int i = 0; i < f.length; ++i) {
			fields.add(f[i]);
		}
	}
	
	public String getString() {
		StringBuilder builder = new StringBuilder();
		String prefix = "";
		
		builder.append("ORDER BY ");
		
		for (Field f : fields) {
			builder.append(prefix);
			builder.append(f.getName());
			
			prefix = ", ";
		}
		
		if (type == TYPE.ASC) {
			builder.append(" ASC");
		} else if (type == TYPE.DESC) {
			builder.append(" DESC");
		}
		
		return builder.toString();
	}
}