package ch.sama.sql.tsql.connection;

import ch.sama.sql.dbo.connection.DBConnection;

public class TSqlConnection extends DBConnection {
    private static final String CONNECTION_PREFIX = "jdbc:jtds:sqlserver://";

    public TSqlConnection(String connectionString, String userName, String password) {
        super(CONNECTION_PREFIX + connectionString, userName, password);
    }
}
