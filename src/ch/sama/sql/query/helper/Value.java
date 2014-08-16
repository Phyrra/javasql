package ch.sama.sql.query.helper;

public interface Value {
	public String toString();
	public Value as(String alias);
}