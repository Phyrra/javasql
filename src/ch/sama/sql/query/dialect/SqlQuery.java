package ch.sama.sql.query.dialect;

import ch.sama.sql.query.base.Query;

public class SqlQuery extends Query {
	public SqlQuery() {
		super(new SqlQueryFactory());
	}
	
	@Override
	public String toString() {
		return null;
	}
}
