package ch.sama.sql.dbo.result.obj;

import ch.sama.sql.dbo.result.IResultSetTransformer;
import ch.sama.sql.dbo.result.TransformerHelper;
import ch.sama.sql.jpa.JpaException;
import ch.sama.sql.jpa.JpaUtil;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObjectTransformer<T> implements IResultSetTransformer<List<T>> {
    private Class<T> clazz;

    public ObjectTransformer(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public List<T> transform(ResultSet resultSet) throws SQLException, JpaException {
        List<T> result = new ArrayList<T>();

        String[] colNames = TransformerHelper.getColumnNames(resultSet);

        JpaUtil util = new JpaUtil();
        List<Field> columns = util.getColumns(clazz);

        while (resultSet.next()) {
            T instance;

            try {
                instance = clazz.newInstance();

                for (int i = 0; i < colNames.length; ++i) {
                    String colName = colNames[i];
                    Object val = resultSet.getObject(i + 1);

                    if (val != null) {
                        for (Field column : columns) {
                            if (util.isColumn(colName, column)) {
                                column.set(
                                        instance,
                                        TransformerHelper.defaultTransform(val)
                                );
                            }
                        }
                    }
                }
            } catch (IllegalAccessException | InstantiationException e) {
                throw new JpaException(e);
            }

            result.add(instance);
        }

        return result;
    }
}