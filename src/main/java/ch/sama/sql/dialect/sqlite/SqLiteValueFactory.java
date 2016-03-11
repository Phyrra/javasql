package ch.sama.sql.dialect.sqlite;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.standard.ValueFactory;

public class SqLiteValueFactory extends ValueFactory {
    private static final SqLiteQueryRenderer renderer = new SqLiteQueryRenderer();

    public SqLiteValueFactory() {
        super(renderer);
    }

    @Override
    public Value object(Object o) {
        throw new BadSqlException("Not implemented");
    }

    @Override
    public Value object(Field field, Object object) {
        throw new BadSqlException("Not implemented");
    }
}