package ch.sama.sql.dialect.mysql.query;

import ch.sama.sql.dialect.mysql.MySqlQueryFactory;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.exception.BadParameterException;

public class MySqlLimitQuery implements IQuery {
    private MySqlQueryFactory factory;
    private IQuery parent;
    private int start;
    private int stop;

    public MySqlLimitQuery(MySqlQueryFactory factory, IQuery parent, int start, int stop) {
        if (start <= 0 || stop <= 0) {
            throw new BadParameterException("Limits must be > 0");
        }

        this.factory = factory;
        this.parent = parent;
        this.start = start;
        this.stop = stop;
    }

    @Override
    public IQuery getParent() {
        return parent;
    }

    @Override
    public String getSql() {
        return factory.renderer().render(this);
    }

    @Override
    public IQuery chainTo(IQuery query) {
        this.parent = query;
        return query;
    }
}
