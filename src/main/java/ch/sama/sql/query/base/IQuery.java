package ch.sama.sql.query.base;

public interface IQuery {
	public IQuery getParent();
    public String getSql();
    public IQuery chainTo(IQuery query);
}