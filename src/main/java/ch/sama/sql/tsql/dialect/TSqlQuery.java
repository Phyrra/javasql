package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.Query;
import ch.sama.sql.query.base.QueryFactory;

public class TSqlQuery extends Query {
	public TSqlQuery() {
		super(new TSqlQueryFactory());
	}

    public TSqlQuery(QueryFactory factory) {
        super(factory);
    }

    public TSqlQuery(QueryFactory factory, IQuery parent) {
        super(factory, parent);
    }
	
	@Override
	public String toString() {
        IQuery parent = getParent();
        if (parent != null) {
            return parent.toString() + " UNION ALL";
        }

		return null;
	}
}
