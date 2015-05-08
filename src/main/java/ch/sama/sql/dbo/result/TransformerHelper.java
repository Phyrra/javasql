package ch.sama.sql.dbo.result;

import ch.sama.sql.query.exception.BadSqlException;

import java.io.BufferedReader;
import java.math.BigInteger;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

class TransformerHelper {
    static String[] getColumnNames(ResultSet set) throws SQLException {
        ResultSetMetaData meta = set.getMetaData();

        int colCount = meta.getColumnCount();
        String[] colNames = new String[colCount];

        for (int i = 0; i < colCount; ++i) {
            colNames[i] = meta.getColumnName(i + 1);
        }

        return colNames;
    }

    static Object defaultTransform(Object o) {
        if (o instanceof Clob) {
            Clob clob = (Clob) o;

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
        } else if (o instanceof BigInteger) {
            BigInteger bi = (BigInteger) o;

            return bi.longValue();
        } else {
            return o;
        }
    }
}
