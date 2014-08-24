package ch.sama.sql.query.base.checker;

import ch.sama.sql.query.base.IQuery;

public class QueryFinder {
    public QueryFinder() { }

    public<T extends IQuery> T findQuery(IQuery src, Class<T> dst) {
        if (src == null) {
            return null;
        }

        if (dst.isAssignableFrom(src.getClass())) {
            return (T)src;
        }

        return findQuery(src.getParent(), dst);
    }
}
