package ch.sama.sql.dbo.connection;

import java.util.List;

public interface IQueryExecutor<R> {
    public void execute(String query);

    public List<R> query(String query);

    public R get(String query);
}
