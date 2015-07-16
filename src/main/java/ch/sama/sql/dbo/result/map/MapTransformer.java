package ch.sama.sql.dbo.result.map;

import ch.sama.sql.dbo.result.IResultSetTransformer;
import ch.sama.sql.dbo.result.TransformerHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MapTransformer implements IResultSetTransformer<List<MapResult>> {
    @Override
    public List<MapResult> transform(ResultSet resultSet) throws SQLException {
        String[] colNames = TransformerHelper.getColumnNames(resultSet);

        List<MapResult> result = new ArrayList<MapResult>();

        while (resultSet.next()) {
            MapResult row = new MapResult();
            
            for (int i = 0; i < colNames.length; ++i) {
                Object val = resultSet.getObject(i + 1);

                row.put(
                        colNames[i],
                        TransformerHelper.defaultTransform(val)
                );
            }

            result.add(row);
        }

        return result;
    }
}
