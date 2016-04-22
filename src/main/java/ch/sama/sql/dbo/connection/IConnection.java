package ch.sama.sql.dbo.connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Database connection
 * will create a connection based on login information
 * should store the connection object until closing
 */
public interface IConnection {
    /**
     * Create and open a new database connection
     * @return new connection
     * @throws SQLException if the connection cannot be established
     */
    public Connection open() throws SQLException;

    /**
     * Close an existing database connection
     * should silently ignore closing exceptions
     */
    public void close();
}
