package ch.sama.sql.dbo.connection;

import org.antlr.v4.runtime.misc.NotNull;

/**
 * Executor for queries
 * will transform the result into a generic set
 * @param <S> a generic return type for the result
 */
public interface IQueryExecutor<S> {
    /**
     * Execute a query without result
     * such as update or insert
     * @param query the query to be executed
     */
    public void execute(@NotNull String query);

    /**
     * Execute a query that returns a result set
     * @param query the query to be executed
     * @return a transformed set
     */
    public S query(@NotNull String query);
}
