package ch.sama.sql.query.base;

public interface IQueryRenderer {
    public String render(Query query);
    public String render(CTEQuery query);
    public String render(CTEQueryFinal query);
    public String render(SelectQuery query);
    public String render(FromQuery query);
    public String render(JoinQuery query);
    public String render(JoinQueryFinal query);
    public String render(WhereQuery query);
    public String render(OrderQuery query);
}
