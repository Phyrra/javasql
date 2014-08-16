package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.ConditionParser;
import ch.sama.sql.query.helper.Order;
import ch.sama.sql.query.helper.OrderParser;
import ch.sama.sql.query.helper.Value;

public interface QueryFactory {
	public SelectQuery selectQuery(QueryFactory factory, IQuery parent, Value... v);
	public FromQuery fromQuery(QueryFactory factory, IQuery parent, Table... t);
	public JoinQuery joinQuery(QueryFactory factory, IQuery parent, Table t); 
	public WhereQuery whereQuery(QueryFactory factory, IQuery parent, Condition condition);
	public OrderQuery orderQuery(QueryFactory factory, IQuery parent, Order order);
	public CTEQuery cteQuery(QueryFactory factory, IQuery parent, String name);
	
	public ConditionParser conditionParser();
	public OrderParser orderParser();
}
