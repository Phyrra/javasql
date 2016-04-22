package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.helper.Source;

/**
 * Factory for creating SQL sources
 */
public interface ISourceFactory {
    public Source table(Table table);
    public Source table(String name);
    public Source table(String schema, String name);
    public Source query(IQuery query);
}
