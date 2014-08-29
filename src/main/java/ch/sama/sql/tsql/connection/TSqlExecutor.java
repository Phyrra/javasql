package ch.sama.sql.tsql.connection;

import ch.sama.sql.dbo.connection.DBConnection;
import ch.sama.sql.dbo.connection.QueryExecutor;
import ch.sama.sql.dbo.connection.ResultSetTransformer;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.exception.BadSqlException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TSqlExecutor implements QueryExecutor {
    private DBConnection connection;
    private ResultSetTransformer transformer;

    public TSqlExecutor(DBConnection connection, ResultSetTransformer transformer) {
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
        Statement statement = null;
        try {
            Connection con = connection.open();
            statement = con.createStatement();

            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            connection.close();
        }
    }

    public void execute(IQuery query) {
        execute(query.toString());
    }

    @Override
    public List<Map<String, Object>> query(String query) {
        List<Map<String, Object>> list = null;

        Statement statement = null;
        try {
            Connection con = connection.open();
            statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            list = transformer.transform(resultSet);
        } catch (Exception e) {
            throw new BadSqlException(e.getMessage());
        } finally {
            closeStatement(statement);
            connection.close();
        }

        return list;
    }

    public List<Map<String, Object>> query(IQuery query) {
        return query(query.toString());
    }

    @Override
    public Map<String, Object> get(String query) {
        List<Map<String, Object>> list = null;

        Statement statement = null;
        try {
            Connection con = connection.open();
            statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            list = transformer.transform(resultSet);
        } catch (Exception e) {
            throw new BadSqlException(e.getMessage());
        } finally {
            closeStatement(statement);
            connection.close();
        }

        if (list.size() != 1) {
            throw new BadSqlException("Expected 1 Result, got " + list.size());
        }

        return list.get(0);
    }

    public Map<String, Object> get(IQuery query) {
        return get(query.toString());
    }
}
