package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.ConditionParser;
import ch.sama.sql.query.helper.Order;
import ch.sama.sql.query.helper.OrderParser;
import ch.sama.sql.query.helper.Value;

import java.util.Date;

public interface QueryFactory {
    public Query create();
    public Query create(QueryFactory factory, IQuery parent);

	public SelectQuery selectQuery(QueryFactory factory, IQuery parent, Value... v);
	public FromQuery fromQuery(QueryFactory factory, IQuery parent, Table... t);
	public JoinQuery joinQuery(QueryFactory factory, IQuery parent, Table t); 
	public WhereQuery whereQuery(QueryFactory factory, IQuery parent, Condition condition);
	public OrderQuery orderQuery(QueryFactory factory, IQuery parent, Order order);
	public CTEQuery cteQuery(QueryFactory factory, IQuery parent, String name);
	
	public ConditionParser conditionParser();
	public OrderParser orderParser();

    public Table table(String name);

    public Value field(String field);
    public Value field(String table, String field);
    public Value date(Date date);
    public Value string(String s);
    public Value numeric(Integer i);
    public Value numeric(Float f);
    public Value numeric(Double d);
    public Value function(String fnc);
    public Value query(IQuery query);
    public Value value(String value);
}
