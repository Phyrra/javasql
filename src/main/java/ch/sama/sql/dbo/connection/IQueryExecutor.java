package ch.sama.sql.dbo.connection;

public interface IQueryExecutor<S> {
    public void execute(String query);

    public S query(String query);
}
