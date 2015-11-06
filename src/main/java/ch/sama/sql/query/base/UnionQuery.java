package ch.sama.sql.query.base;

import java.util.Arrays;
import java.util.List;

public class UnionQuery implements IQuery {
    private IQueryRenderer renderer;
    private IQuery parent;
    private List<IQuery> queries;

    public UnionQuery(IQueryRenderer renderer, IQuery parent, IQuery[] queries) {
        this.renderer = renderer;
        this.parent = parent;

        this.queries = Arrays.asList(queries);
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
        this.parent = query;
        return query;
    }

    public List<IQuery> getQueries() {
        return queries;
    }
}