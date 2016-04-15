package ch.sama.sql.dbo.connection;

import ch.sama.sql.dbo.result.IResultSetTransformer;

public interface IQueryExecutor {
    public void execute(String query);

    public<S> S query(String query, IResultSetTransformer<S> transformer);
}
