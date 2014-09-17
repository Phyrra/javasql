package ch.sama.sql.tsql.dialect;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Function;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.*;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.ConditionParser;
import ch.sama.sql.query.helper.Order;
import ch.sama.sql.query.helper.OrderParser;
import ch.sama.sql.query.helper.Value;

import java.util.Date;

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

    @Override
    public Table table(String name) {
        return new TSqlTable(name);
    }

    @Override
    public Table table(String schema, String name) {
        return new TSqlTable(schema, name);
    }

    @Override
    public Value tableFields(String table) {
        return TSqlValue.plain("[" + table + "].*");
    }

    @Override
    public Value tableFields(Table table) {
        return TSqlValue.plain(table.toString() + ".*");
    }

    @Override
    public Value field(Field field) {
        return new TSqlValue(field);
    }

    @Override
    public Value field(String field) {
        return new TSqlValue(new TSqlField(field));
    }

    @Override
    public Value field(String table, String field) {
        return new TSqlValue(new TSqlField(table, field));
    }

    @Override
    public Value field(Table table, String field) {
        return new TSqlValue(new TSqlField(table, field));
    }

    @Override
    public Value plain(String s) {
        return TSqlValue.plain(s);
    }

    @Override
    public Value date(Date date) {
        return new TSqlValue(date);
    }

    @Override
    public Value string(String s) {
        return new TSqlValue(s);
    }

    @Override
    public Value numeric(Integer i) {
        return new TSqlValue(i);
    }

    @Override
    public Value numeric(Float f) {
        return new TSqlValue(f);
    }

    @Override
    public Value numeric(Double d) {
        return new TSqlValue(d);
    }

    @Override
    public Value function(String fnc) {
        return new TSqlValue(new Function(fnc));
    }

    @Override
    public Value query(IQuery query) {
        return new TSqlValue(query);
    }

    @Override
    public Value value(Value.VALUE value) {
        return new TSqlValue(value);
    }
}
