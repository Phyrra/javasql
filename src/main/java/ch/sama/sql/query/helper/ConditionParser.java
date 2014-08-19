package ch.sama.sql.query.helper;

public interface ConditionParser {
    public String eq(Condition c);
    public String neq(Condition c);
    public String like(Condition c);
	public String and(Condition c);
    public String or(Condition c);
    public String not(Condition c);
    public String gt(Condition c);
    public String ge(Condition c);
    public String lt(Condition c);
    public String le(Condition c);
    public String exists(Condition c);
    public String isNull(Condition c);
    public String in(Condition c);
}
