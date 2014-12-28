package ch.sama.sql.query.base.check;

import ch.sama.sql.query.base.FromQuery;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.JoinQuery;
import ch.sama.sql.query.base.SelectQuery;
import ch.sama.sql.query.helper.Source;
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
            return dst.cast(src);
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
    public<T> List<T> getSelected(IQuery src, Class<T> dst) {
        SelectQuery query = get(src, SelectQuery.class);
        List<Value> values = query.getValues();

        List<T> fields = new ArrayList<T>();
        for (Value v : values) {
            Object o = v.getSource();

            if (o != null) {
                if (dst.isAssignableFrom(o.getClass())) {
                    fields.add(dst.cast(o));
                }
            }
        }

        return fields;
    }

    public List<Source> getSources(IQuery src) {
        List<Source> sources = new ArrayList<Source>();

        FromQuery from = get(src, FromQuery.class); // There can be only one
        List<JoinQuery> joins = getAll(src, JoinQuery.class);

        List<Source> tmp = from.getSources();
        for (Source s : tmp) {
            sources.add(s);
        }

        for (JoinQuery j : joins) {
            sources.add(j.getSource());
        }

        return sources;
    }

    public<T> List<T> getSources(IQuery src, Class<T> dst) {
        List<T> sources = new ArrayList<T>();

        FromQuery from = get(src, FromQuery.class); // There can be only one
        List<JoinQuery> joins = getAll(src, JoinQuery.class);

        List<Source> tmp = from.getSources();
        for (Source s : tmp) {
            Object o = s.getSource();

            if (o != null) {
                if (dst.isAssignableFrom(o.getClass())) {
                    sources.add(dst.cast(o));
                }
            }
        }

        for (JoinQuery j : joins) {
            Source s = j.getSource();
            Object o = s.getSource();

            if (o != null) {
                if (dst.isAssignableFrom(o.getClass())) {
                    sources.add(dst.cast(o));
                }
            }
        }

        return sources;
    }
}
