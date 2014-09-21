package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.helper.*;

import java.util.Date;

public interface QueryFactory {
    public Query create();
    public Query create(QueryFactory factory, IQuery parent);

	public SelectQuery selectQuery(QueryFactory factory, IQuery parent, Value... v);
	public FromQuery fromQuery(QueryFactory factory, IQuery parent, Source... s);
	public JoinQuery joinQuery(QueryFactory factory, IQuery parent, Source s);
	public WhereQuery whereQuery(QueryFactory factory, IQuery parent, Condition condition);
	public OrderQuery orderQuery(QueryFactory factory, IQuery parent, Order order);
	public CTEQuery cteQuery(QueryFactory factory, IQuery parent, String name);
	
	public ConditionParser conditionParser();
	public OrderParser orderParser();

    public Source table(String name);
    public Source table(String schema, String name);
    public Source query(IQuery query);
}
