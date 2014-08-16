package ch.sama.sql.dialect.tsql;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.CTEQuery;
import ch.sama.sql.query.base.FromQuery;
import ch.sama.sql.query.base.JoinQuery;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.OrderQuery;
import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.query.base.SelectQuery;
import ch.sama.sql.query.base.WhereQuery;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.ConditionParser;
import ch.sama.sql.query.helper.Order;
import ch.sama.sql.query.helper.OrderParser;

public class TSqlQueryFactory implements QueryFactory {
	@Override
	public SelectQuery selectQuery(QueryFactory factory, IQuery parent, Field... f) {
		return new TSqlSelectQuery(factory, parent, f);
	}

	@Override
	public FromQuery fromQuery(QueryFactory factory, IQuery parent, Table... t) {
		return new TSqlFromQuery(factory, parent, t);
	}
	
	@Override
	public JoinQuery joinQuery(QueryFactory factory, IQuery parent, Table t) {
		return new TSqlJoinQuery(factory, parent, t);
	}

	@Override
	public WhereQuery whereQuery(QueryFactory factory, IQuery parent, Condition condition) {
		return new TSqlWhereQuery(factory, parent, condition);
	}

	@Override
	public OrderQuery orderQuery(QueryFactory factory, IQuery parent, Order order) {
		return new TSqlOrderQuery(factory, parent, order);
	}
	
	@Override public CTEQuery cteQuery(QueryFactory factory, IQuery parent, String name) {
		return new TSqlCTEQuery(factory, parent, name);
	}

	@Override
	public ConditionParser conditionParser() {
		return new TSqlConditionParser();
	}

	@Override
	public OrderParser orderParser() {
		return new TSqlOrderParser();
	}
}
