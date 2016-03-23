package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.exception.BadParameterException;

public class TSqlOffsetQuery implements IQuery {
    private TSqlQueryRenderer renderer;
    private IQuery parent;

    private int offset;
    private int limit;

    public TSqlOffsetQuery(TSqlQueryRenderer renderer, IQuery parent, int offset) {
        if (offset < 0) {
            throw new BadParameterException("Offset must be >= 0");
        }

        this.renderer = renderer;
        this.parent = parent;

        this.offset = offset;
        this.limit = -1;
    }

    public TSqlOffsetQuery(TSqlQueryRenderer renderer, IQuery parent, int offset, int limit) {
        if (offset < 0) {
            throw new BadParameterException("Offset must be >= 0");
        }

        if (limit <= 0) {
            throw new BadParameterException("Limit must be > 0");
        }

        this.renderer = renderer;
        this.parent = parent;

        this.offset = offset;
        this.limit = limit;
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

    public int getOffset() {
        return offset;
    }

    public boolean hasLimit() {
        return limit > 0;
    }

    public int getLimit() {
        return limit;
    }
}
