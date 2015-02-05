package ch.sama.sql.dbo.connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IResultSetTransformer {
    public List<Map<String, Object>> transform(ResultSet resultSet) throws SQLException;
}
