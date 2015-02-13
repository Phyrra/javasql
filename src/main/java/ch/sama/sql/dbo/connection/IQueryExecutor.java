package ch.sama.sql.dbo.connection;

import ch.sama.sql.dbo.result.IResultSet;
import ch.sama.sql.dbo.result.ResultRow;

public interface IQueryExecutor {
    public void execute(String query);

    public IResultSet query(String query);

    public ResultRow get(String query);
}
