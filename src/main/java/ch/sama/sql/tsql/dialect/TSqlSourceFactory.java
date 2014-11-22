package ch.sama.sql.tsql.dialect;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.base.ISourceFactory;
import ch.sama.sql.query.helper.Source;

class TSqlSourceFactory implements ISourceFactory {
    private IQueryRenderer renderer = new TSqlQueryRenderer();

    @Override
    public Source table(Table table) {
        return new Source(table, table.getString(renderer));
    }
    @Override
    public Source table(String schema, String name) {
        return table(new Table(schema, name));
    }

    @Override
    public Source table(String name) {
        return table(new Table(name));
    }

    @Override
    public Source query(IQuery query) {
        return new Source(query, "(\n" + query.getSql() + "\n)");
    }
}
