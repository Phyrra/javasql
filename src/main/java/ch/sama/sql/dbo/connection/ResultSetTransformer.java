package ch.sama.sql.dbo.connection;

import ch.sama.sql.query.exception.BadSqlException;

import java.io.BufferedReader;
import java.sql.Clob;
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

                if (val instanceof Clob) {
                    map.put(colNames[i], clobToString((Clob)val));
                } else {
                    map.put(colNames[i], val);
                }
            }

            list.add(map);
        }

        return list;
    }

    private String clobToString(Clob clob) {
        StringBuilder builder = new StringBuilder();

        String line;
        BufferedReader bufferedReader;
        try {
            String lineBreak = "";
            bufferedReader = new BufferedReader(clob.getCharacterStream());

            while ((line = bufferedReader.readLine()) != null) {
                builder.append(lineBreak);
                builder.append(line);

                lineBreak = "\n";
            }
        } catch (Exception e) {
            throw new BadSqlException(e);
        }

        return builder.toString();
    }
}
