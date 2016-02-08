package ch.sama.sql.dbo.result;

import org.antlr.v4.runtime.misc.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Transformer for ResultSet to something generic
 * @param <S> a generic return type for the result
 */
public interface IResultSetTransformer<S> {
    /**
     * Transform a ResultSet to something else
     * @param resultSet the result from a database query
     * @return transformed set
     * @throws SQLException if there is an error in the transformation
     */
    public S transform(@NotNull ResultSet resultSet) throws SQLException;
}