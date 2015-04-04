package ch.sama.sql.query.standard;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.base.ISourceFactory;
import ch.sama.sql.query.helper.Source;

public abstract class SourceFactory implements ISourceFactory {
    private IQueryRenderer renderer;

    public SourceFactory(IQueryRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public Source table(Table table) {
        return new Source(table, renderer.render(table));
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
