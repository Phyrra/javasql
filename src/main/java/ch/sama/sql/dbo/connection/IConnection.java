package ch.sama.sql.dbo.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnection {
    public Connection open() throws ClassNotFoundException, SQLException;
    public void close();
}
