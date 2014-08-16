package ch.sama.sql.dialect.tsql;

import ch.sama.sql.query.base.Query;

public class TSqlQuery extends Query {
	public TSqlQuery() {
		super(new TSqlQueryFactory());
	}
	
	@Override
	public String toString() {		
		return null;
	}
}
