package ch.sama.sql.query.dialect;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.FromQuery;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.OrderQuery;
import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.query.base.SelectQuery;
import ch.sama.sql.query.base.WhereQuery;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.ConditionParser;
import ch.sama.sql.query.helper.Order;
import ch.sama.sql.query.helper.OrderParser;

public class SqlQueryFactory implements QueryFactory {
	@Override
	public SelectQuery selectQuery(QueryFactory factory, IQuery parent, Field... f) {
		return new SqlSelectQuery(factory, parent, f);
	}

	@Override
	public FromQuery fromQuery(QueryFactory factory, IQuery parent, Table... t) {
		return new SqlFromQuery(factory, parent, t);
	}

	@Override
	public WhereQuery whereQuery(QueryFactory factory, IQuery parent, Condition condition) {
		return new SqlWhereQuery(factory, parent, condition);
	}

	@Override
	public OrderQuery orderQuery(QueryFactory factory, IQuery parent, Order order) {
		return new SqlOrderQuery(factory, parent, order);
	}

	@Override
	public ConditionParser conditionParser() {
		return new SqlConditionParser();
	}

	@Override
	public OrderParser orderParser() {
		return new SqlOrderParser();
	}
}
