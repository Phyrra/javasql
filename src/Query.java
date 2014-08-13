import java.util.*;

public class Query implements IQuery {
	public Query() {
	}
	
	public String getSql() {
		return null;
	}
		
	public SelectQuery select(Field... f) {
		return new SelectQuery(this, f);
	}
}