package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.Value;

public class CTEQueryFinal implements IQuery {
    private IQuery parent;
    private IQuery query;

    public CTEQueryFinal(IQuery parent, IQuery query) {
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
        return new CTEQuery(this, name);
    }

    public SelectQuery select(Value... v) {
        return new SelectQuery(this, v);
    }

    @Override
    public String getSql(IQueryRenderer renderer) {
        return renderer.render(this);
    }
}
