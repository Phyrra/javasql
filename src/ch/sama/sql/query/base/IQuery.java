package ch.sama.sql.query.base;

public interface IQuery {
	public IQuery getParent();
	public String toString();
	public QueryFactory getFactory();
}