package ch.sama.sql.query.helper.order;

public interface IOrderRenderer {
    public String render(DefOrder o);
    public String render(AscOrder o);
    public String render(DescOrder o);
}
