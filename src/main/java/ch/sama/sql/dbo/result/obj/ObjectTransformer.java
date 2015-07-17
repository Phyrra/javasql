package ch.sama.sql.dbo.result.obj;

import ch.sama.sql.dbo.result.IResultSetTransformer;
import ch.sama.sql.dbo.result.TransformerHelper;
import ch.sama.sql.jpa.Entity;

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
    public List<T> transform(ResultSet resultSet) throws SQLException {
        if (!clazz.isAnnotationPresent(Entity.class)) {
            throw new SQLException("Object must be annotated with @Entity");
        }

        List<T> result = new ArrayList<T>();

        String[] colNames = TransformerHelper.getColumnNames(resultSet);
        List<Property> columns = ObjectHelper.getProperties(clazz);

        while (resultSet.next()) {
            T instance;

            try {
                instance = clazz.newInstance();

                for (int i = 0; i < colNames.length; ++i) {
                    String colName = colNames[i];
                    Object val = resultSet.getObject(i + 1);

                    if (val != null) {
                        for (Property column : columns) {
                            if (column.is(colName)) {
                                column.set(instance, TransformerHelper.defaultTransform(val));
                            }
                        }
                    }
                }
            } catch (Exception e) {
                throw new SQLException(e);
            }

            result.add(instance);
        }

        return result;
    }
}