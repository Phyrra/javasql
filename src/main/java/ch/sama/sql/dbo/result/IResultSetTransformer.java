package ch.sama.sql.dbo.result;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IResultSetTransformer<R> {
    public List<R> transform(ResultSet resultSet) throws SQLException;
}