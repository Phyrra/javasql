package ch.sama.sql.query.helper;

import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.IQueryRenderer;

import java.util.List;

public interface ConditionParser {
    public String eq(Value lhs, Value rhs);
    public String neq(Value lhs, Value rhs);
    public String like(Value lhs, Value rhs);
	public String and(List<Condition> conditions);
    public String or(List<Condition> conditions);
    public String not(Condition c);
    public String gt(Value lhs, Value rhs);
    public String ge(Value lhs, Value rhs);
    public String lt(Value lhs, Value rhs);
    public String le(Value lhs, Value rhs);
    public String exists(IQuery query);
    public String isNull(Value v);
    public String in(Value v, IQuery query);
}
