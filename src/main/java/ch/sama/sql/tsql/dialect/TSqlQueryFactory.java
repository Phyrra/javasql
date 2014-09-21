package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.base.*;
import ch.sama.sql.query.helper.*;

public class TSqlQueryFactory implements QueryFactory {
    @Override
    public Query create() {
        return new TSqlQuery(this);
    }

    @Override
    public Query create(QueryFactory factory, IQuery parent) {
        return new TSqlQuery(factory, parent);
    }

	@Override
	public SelectQuery selectQuery(QueryFactory factory, IQuery parent, Value... v) {
		return new TSqlSelectQuery(factory, parent, v);
	}

	@Override
	public FromQuery fromQuery(QueryFactory factory, IQuery parent, Source... s) {
		return new TSqlFromQuery(factory, parent, s);
	}
	
	@Override
	public JoinQuery joinQuery(QueryFactory factory, IQuery parent, Source s) {
		return new TSqlJoinQuery(factory, parent, s);
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

    @Override
    public Source table(String name) {
        return new TSqlSource(new TSqlTable(name));
    }

    @Override
    public Source table(String schema, String name) {
        return new TSqlSource(new TSqlTable(schema, name));
    }

    @Override
    public Source query(IQuery query) {
        return new TSqlSource(query);
    }
}
