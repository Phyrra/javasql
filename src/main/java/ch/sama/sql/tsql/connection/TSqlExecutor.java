package ch.sama.sql.tsql.connection;

import ch.sama.sql.dbo.connection.DBConnection;
import ch.sama.sql.dbo.connection.IQueryExecutor;
import ch.sama.sql.dbo.result.IResultSetTransformer;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.exception.ConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TSqlExecutor<R> implements IQueryExecutor<R> {
    private DBConnection connection;
    private IResultSetTransformer<R> transformer;

    public TSqlExecutor(DBConnection connection, IResultSetTransformer<R> transformer) {
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

    @Override
    public void execute(String query) {
        Statement statement;
        try {
            Connection con = connection.open();
            statement = con.createStatement();
        } catch (Exception e) {
            connection.close();

            throw new ConnectionException(e);
        }

        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            closeStatement(statement);
            connection.close();

            throw new BadSqlException(e);
        }

        closeStatement(statement);
        connection.close();
    }

    @Override
    public List<R> query(String query) {
        List<R> result;

        Statement statement;
        try {
            Connection con = connection.open();
            statement = con.createStatement();
        } catch (Exception e) {
            connection.close();

            throw new ConnectionException(e);
        }

        try {
            ResultSet resultSet = statement.executeQuery(query);
            result = transformer.transform(resultSet);
        } catch (SQLException e) {
            closeStatement(statement);
            connection.close();

            throw new BadSqlException(e);
        }

        closeStatement(statement);
        connection.close();

        return result;
    }

    @Override
    public R get(String query) {
        List<R> result;

        Statement statement;
        try {
            Connection con = connection.open();
            statement = con.createStatement();
        } catch (Exception e) {
            connection.close();

            throw new ConnectionException(e);
        }

        try {
            ResultSet resultSet = statement.executeQuery(query);
            result = transformer.transform(resultSet);
        } catch (SQLException e) {
            closeStatement(statement);
            connection.close();

            throw new BadSqlException(e);
        }

        closeStatement(statement);
        connection.close();

        if (result.size() != 1) {
            throw new BadSqlException("Expected 1 Result, got " + result.size());
        }

        return result.get(0);
    }
}
