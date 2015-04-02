package ch.sama.sql.mysql.connection;

import ch.sama.sql.dbo.connection.DBConnection;

public class MySqlConnection extends DBConnection {
    private static final String CONNECTION_PREFIX = "jdbc:mysql://";

    public MySqlConnection(String connectionString) {
        super(connectionString);
    }

    public MySqlConnection(String connectionString, String userName, String password) {
        super(CONNECTION_PREFIX + connectionString, userName, password);
    }
}
