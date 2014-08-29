package ch.sama.sql.dbo.connection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResultSetTransformer {
    public List<Map<String, Object>> transform(ResultSet resultSet) throws SQLException {
        ResultSetMetaData meta = resultSet.getMetaData();

        int colCount = meta.getColumnCount();
        String[] colNames = new String[colCount];
        for (int i = 0; i < colCount; ++i) {
            colNames[i] = meta.getColumnName(i + 1);
        }

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        while (resultSet.next()) {
            LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
            for (int i = 0; i < colCount; ++i) {
                Object val = resultSet.getObject(i + 1);

                map.put(colNames[i], val);
            }

            list.add(map);
        }

        return list;
    }
}
