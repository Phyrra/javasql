package ch.sama.sql.dbo.result;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapTransformer implements IResultSetTransformer<MapResultList> {
    public MapResultList transform(ResultSet resultSet) throws SQLException {
        String[] colNames = TransformerHelper.getColumnNames(resultSet);

        MapResultList result = new MapResultList();

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
