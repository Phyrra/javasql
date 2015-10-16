package ch.sama.sql.dialect.sqlite.connection;

import ch.sama.sql.dbo.connection.DBConnection;

public class SQLiteConnection extends DBConnection {
    private static final String CONNECTION_PREFIX = "jdbc:sqlite:";

    public SQLiteConnection() {
        super(CONNECTION_PREFIX + ":memory:");
    }

    public SQLiteConnection(String connectionString) {
        super(CONNECTION_PREFIX + connectionString);
    }
}