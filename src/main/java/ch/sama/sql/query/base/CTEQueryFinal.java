package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.Value;

public class CTEQueryFinal implements IQuery {
    IQueryRenderer renderer;
    private IQuery parent;
    private IQuery query;

    public CTEQueryFinal(IQueryRenderer renderer, IQuery parent, IQuery query) {
        this.renderer = renderer;
        this.parent = parent;
        this.query = query;
    }

    @Override
    public IQuery getParent() {
        return parent;
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

    @Override
    public String getSql() {
        return renderer.render(this);
    }
}
