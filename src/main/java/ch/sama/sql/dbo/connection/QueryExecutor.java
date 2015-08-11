package ch.sama.sql.dbo.connection;

import ch.sama.sql.dbo.result.IResultSetTransformer;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.exception.ConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryExecutor<S> implements IQueryExecutor<S> {
    private IConnection connection;
    private IResultSetTransformer<S> transformer;

    public QueryExecutor(IConnection connection, IResultSetTransformer<S> transformer) {
        this.connection = connection;
        this.transformer = transformer;
    }

    private void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) { }
    }

    private Statement createStatement() {
        try {
            Connection con = connection.open();
            return con.createStatement();
        } catch (SQLException e) {
            connection.close();

            throw new ConnectionException(e);
        }
    }

    @Override
    public void execute(String query) {
        Statement statement = createStatement();

        try {
            statement.execute(query);
        } catch (SQLException e) {
            throw new BadSqlException(e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
    }

    @Override
    public S query(String query) {
        Statement statement = createStatement();

        try {
            ResultSet resultSet = statement.executeQuery(query);
            return transformer.transform(resultSet);
        } catch (SQLException e) {
            throw new BadSqlException(e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
    }
}
