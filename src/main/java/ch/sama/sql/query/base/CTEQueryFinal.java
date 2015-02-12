package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.helper.Value;

public class CTEQueryFinal implements IQuery {
    private IQueryRenderer renderer;
    private CTEQuery parent;
    private IQuery query;

    public CTEQueryFinal(IQueryRenderer renderer, CTEQuery parent, IQuery query) {
        this.renderer = renderer;
        this.parent = parent;
        this.query = query;
    }

    @Override
    public IQuery getParent() {
        return parent;
    }

    @Override
    public String getSql() {
        return renderer.render(this);
    }

    @Override
    public IQuery chainTo(IQuery query) {
        return this.parent.chainTo(query);
    }

    public String getCteName() {
        return parent.getName();
    }

    public IQuery getQuery() {
        return query;
    }

    public CTEQuery with(String name) {
        return new CTEQuery(renderer, this, name);
    }

    public SelectQuery select(Value... v) {
        return new SelectQuery(renderer, this, v);
    }

    public InsertQuery insert() {
        return new InsertQuery(renderer, this);
    }

    public DeleteQuery delete() {
        return new DeleteQuery(renderer, this);
    }

    public UpdateQuery update(Table table) {
        return new UpdateQuery(renderer, this, table);
    }

    public UpdateQuery update(String table) {
        return update(new Table(table));
    }
}
