package ch.sama.sql.dialect.mysql;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.standard.ValueFactory;

import java.text.SimpleDateFormat;

public class MySqlValueFactory extends ValueFactory {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final MySqlQueryRenderer renderer = new MySqlQueryRenderer();

    public MySqlValueFactory() {
        super(renderer);
    }

    public Value table(String table) {
        return table(new Table(table));
    }

    public Value table(Table table) {
        return new Value(table, renderer.render(table) + ".*");
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
