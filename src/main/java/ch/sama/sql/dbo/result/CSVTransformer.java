package ch.sama.sql.dbo.result;

import ch.sama.sql.csv.CSVRow;
import ch.sama.sql.csv.CSVSet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CSVTransformer implements IResultSetTransformer<CSVSet> {
    @Override
    public CSVSet transform(ResultSet resultSet) throws SQLException {
        String[] colNames = TransformerHelper.getColumnNames(resultSet);

        CSVSet result = new CSVSet();

        while (resultSet.next()) {
            CSVRow row = new CSVRow();

            for (int i = 0; i < colNames.length; ++i) {
                Object val = resultSet.getObject(i + 1);

                row.add(
                        val == null ? "" : TransformerHelper.defaultTransform(val).toString().replace("\r", "").replace("\n", " ")
                );
            }

            result.add(row);
        }

        return result;
    }
}
