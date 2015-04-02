package ch.sama.sql.dbo.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection implements IConnection {
    private Connection connection;

    private String connectionString;
    private String userName;
    private String password;

    public DBConnection(String connectionString, String userName, String password) {
        this.connectionString = connectionString;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public Connection open() throws SQLException {
        this.connection = DriverManager.getConnection(connectionString, userName, password);

        return this.connection;
    }

    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) { }
    }
}
