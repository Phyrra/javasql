package ch.sama.sql.dbo.result;

import ch.sama.sql.dbo.connection.DBConnection;

public class SQLiteConnection extends DBConnection {
    private static final String CONNECTION_PREFIX = "jdbc:sqlite:";

    public SQLiteConnection(String connectionString) {
        super(CONNECTION_PREFIX + connectionString);
    }
}
