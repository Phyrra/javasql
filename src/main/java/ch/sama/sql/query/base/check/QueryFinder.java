package ch.sama.sql.query.base.check;

import ch.sama.sql.query.base.FromQuery;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.JoinQuery;
import ch.sama.sql.query.base.SelectQuery;
import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public<T> List<T> getValues(IQuery src, Class<T> dst) {
        SelectQuery query = get(src, SelectQuery.class);
        List<Value> values = query.getValues();

        return values.stream()
                .filter(v -> v.getSource() != null)
                .map(Value::getSource)
                .filter(o -> dst.isAssignableFrom(o.getClass()))
                .map(dst::cast)
                .collect(Collectors.toList());
    }

    public List<Source> getSources(IQuery src) {
        List<Source> sources = new ArrayList<Source>();

        FromQuery from = get(src, FromQuery.class); // There can be only one
        List<JoinQuery> joins = getAll(src, JoinQuery.class);

        List<Source> tmp = from.getSources();

        sources.addAll(
                tmp.stream()
                        .collect(Collectors.toList())
        );

        sources.addAll(
                joins.stream()
                        .map(JoinQuery::getSource)
                        .collect(Collectors.toList())
        );

        return sources;
    }

    // TODO: This will not work with "UNION ALL" (what should even be returned?)
    public<T> List<T> getSources(IQuery src, Class<T> dst) {
        List<T> sources = new ArrayList<T>();

        FromQuery from = get(src, FromQuery.class); // There can be only one
        List<JoinQuery> joins = getAll(src, JoinQuery.class);

        List<Source> tmp = from.getSources();

        sources.addAll(
                tmp.stream()
                        .filter(s -> s.getSource() != null)
                        .map(Source::getSource)
                        .filter(o -> dst.isAssignableFrom(o.getClass()))
                        .map(dst::cast)
                        .collect(Collectors.toList())
        );

        sources.addAll(
                joins.stream()
                        .filter(j -> j.getSource() != null)
                        .map(JoinQuery::getSource)
                        .filter(s -> s.getSource() != null)
                        .map(Source::getSource)
                        .filter(o -> dst.isAssignableFrom(o.getClass()))
                        .map(dst::cast)
                        .collect(Collectors.toList())
        );

        return sources;
    }
}
