package ch.sama.sql.dialect.tsql;

import ch.sama.sql.query.base.Query;
import ch.sama.sql.query.base.QueryFactory;

public class TSqlQuery extends Query {
	public TSqlQuery() {
		super(new TSqlQueryFactory());
	}

    public TSqlQuery(QueryFactory factory) {
        super(factory);
    }
	
	@Override
	public String toString() {		
		return null;
	}
}
