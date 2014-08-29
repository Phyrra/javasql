package ch.sama.sql.dbo.connection;

import java.sql.Connection;

public interface DBConnection {
    public Connection open() throws Exception;
    public void close();
}
