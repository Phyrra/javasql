package ch.sama.sql.dbo.result;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NopTransformer implements IResultSetTransformer<ResultSet> {
    @Override
    public ResultSet transform(ResultSet resultSet) throws SQLException {
        return resultSet;
    }
}
