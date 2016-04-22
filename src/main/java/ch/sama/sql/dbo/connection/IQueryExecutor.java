package ch.sama.sql.dbo.connection;

import ch.sama.sql.dbo.result.IResultSetTransformer;

/**
 * Executor for queries
 */
public interface IQueryExecutor {
    /**
     * Execute a query without a result
     * such as update or insert
     * @param query the query to be executed
     */
    public void execute(String query);

    /**
     * Execute a query that returns a result set
     * the returning value will be transformed into a generic result
     * @param query the query to be executed
     * @param transformer the transformer to use on every row
     * @param <S> the return type of the generic result
     * @return a generic result set
     */
    public<S> S query(String query, IResultSetTransformer<S> transformer);
}
