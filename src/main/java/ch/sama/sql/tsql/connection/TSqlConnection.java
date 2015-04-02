package ch.sama.sql.tsql.connection;

import ch.sama.sql.dbo.connection.DBConnection;

public class TSqlConnection extends DBConnection {
    private static final String JDBC_CLASS = "net.sourceforge.jtds.jdbc.Driver";
    private static final String CONNECTION_PREFIX = "jdbc:jtds:sqlserver://";

    public TSqlConnection(String connectionString, String userName, String password) {
        super(JDBC_CLASS, CONNECTION_PREFIX + connectionString, userName, password);
    }
}
