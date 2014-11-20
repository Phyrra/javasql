package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.ISourceFactory;
import ch.sama.sql.query.helper.Source;

public class TSqlSourceFactory implements ISourceFactory {
    @Override
    public Source table(String schema, String name) {
        return new TSqlSource(new TSqlTable(schema, name));
    }

    @Override
    public Source table(String name) {
        return new TSqlSource(new TSqlTable(name));
    }

    @Override
    public Source query(IQuery query) {
        return new TSqlSource(query);
    }
}
