package ch.sama.sql.mysql.connection;

import ch.sama.sql.dbo.connection.DBConnection;

public class MySqlConnection extends DBConnection {
    private static final String JDBC_CLASS = "com.mysql.jdbc.Driver";
    private static final String CONNECTION_PREFIX = "jdbc:mysql://";

    public MySqlConnection(String connectionString, String userName, String password) {
        super(JDBC_CLASS, CONNECTION_PREFIX + connectionString, userName, password);
    }
}
