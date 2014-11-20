package ch.sama.sql.query.base;

import ch.sama.sql.query.helper.Source;

public interface ISourceFactory {
    public Source table(String schema, String name);
    public Source table(String name);
    public Source query(IQuery query);
}
