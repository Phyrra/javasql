package ch.sama.sql.query.helper.condition;

public interface IConditionRenderer {
    public String render(EqCondition c);
    public String render(NeqCondition c);
    public String render(LikeCondition c);
    public String render(AndCondition c);
    public String render(OrCondition c);
    public String render(NotCondition c);
    public String render(GtCondition c);
    public String render(GeCondition c);
    public String render(LtCondition c);
    public String render(LeCondition c);
    public String render(ExistsCondition c);
    public String render(IsNullCondition c);
    public String render(InQueryCondition c);
    public String render(CustomCondition c);
}
