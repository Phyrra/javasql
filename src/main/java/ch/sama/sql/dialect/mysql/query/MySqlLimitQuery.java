package ch.sama.sql.dialect.mysql.query;

import ch.sama.sql.dialect.mysql.MySqlQueryRenderer;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.exception.BadParameterException;

public class MySqlLimitQuery implements IQuery {
    private MySqlQueryRenderer renderer;
    private IQuery parent;

    private int limit;
    private int start;
    private int stop;

    public MySqlLimitQuery(MySqlQueryRenderer renderer, IQuery parent, int limit) {
        if (limit <= 0) {
            throw new BadParameterException("Limit must be > 0");
        }

        this.renderer = renderer;
        this.parent = parent;

        this.limit = limit;
        this.start = -1;
        this.stop = -1;
    }

    public MySqlLimitQuery(MySqlQueryRenderer renderer, IQuery parent, int start, int stop) {
        if (start <= 0 || stop <= 0) {
            throw new BadParameterException("Limits must be > 0");
        }

        this.renderer = renderer;
        this.parent = parent;

        this.limit = -1;
        this.start = start;
        this.stop = stop;
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

    public boolean hasLimit() {
        return limit > 0;
    }

    public boolean hasRange() {
        return start > 0 && stop > 0;
    }

    public int getLimit() {
        return limit;
    }

    public int[] getRange() {
        return new int[] { start, stop };
    }
}
