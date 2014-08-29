package ch.sama.sql.tsql.connection;

import ch.sama.sql.dbo.connection.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JtdsConnection implements DBConnection {
    private Connection connection;
    private String connectionString;
    private String userName;
    private String password;

    public JtdsConnection(String connectionString, String userName, String password) {
        this.connectionString = "jdbc:jtds:sqlserver://" + connectionString;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public Connection open() throws Exception {
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        this.connection = DriverManager.getConnection(connectionString, userName, password);

        return this.connection;
    }

    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) { }
    }
}
