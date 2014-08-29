package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.base.CTEQuery;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.QueryFactory;

public class TSqlCTEQuery extends CTEQuery {
	public TSqlCTEQuery(QueryFactory factory, IQuery parent, String name) {
		super(new TSqlQueryFactory(), parent, name);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

        IQuery parent = getParent();
        if (parent instanceof CTEQuery) {
            builder.append(parent.toString());
            builder.append(", ");
        } else {
            builder.append("WITH ");
        }

		builder.append(getName());
		builder.append(" AS (\n");
		builder.append(getQuery().toString());
		builder.append("\n)");
		
		return builder.toString();
	}
}
