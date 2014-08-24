package ch.sama.sql.query.base.checker;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.SelectQuery;
import ch.sama.sql.query.helper.Value;

import java.util.ArrayList;
import java.util.List;

public class QueryFinder {
    public QueryFinder() { }

    public<T extends IQuery> T get(IQuery src, Class<T> dst) {
        if (src == null) {
            return null;
        }

        if (dst.isAssignableFrom(src.getClass())) {
            return (T)src;
        }

        return get(src.getParent(), dst);
    }

    public<T extends IQuery> List<T> getAll(IQuery src, Class<T> dst) {
        List<T> list = new ArrayList<T>();

        IQuery iter = src;
        T query;


        while ((query = get(iter, dst)) != null) {
            list.add(query);
            iter = query.getParent();
        }

        return list;
    }

    // TODO: This will not work with "SELECT *"
    // TODO: This will not work with "SELECT TABLE.*"
    public List<Field> getSelectedFields(IQuery src) {
        SelectQuery query = get(src, SelectQuery.class);
        List<Value> values = query.getValues();

        List<Field> fields = new ArrayList<Field>();
        for (Value v : values) {
            Object o = v.getSource();
            if (o instanceof Field) {
                fields.add((Field)o);
            }
        }

        return fields;
    }
}
