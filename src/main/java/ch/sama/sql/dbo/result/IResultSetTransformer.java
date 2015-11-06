package ch.sama.sql.dbo.result;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IResultSetTransformer<S> {
    public S transform(ResultSet resultSet) throws SQLException;
}