package ch.sama.sql.dialect.mysql;

import ch.sama.sql.query.exception.BadParameterException;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.standard.ValueFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class MySqlValueFactory extends ValueFactory {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final MySqlQueryRenderer renderer = new MySqlQueryRenderer();

    public MySqlValueFactory() {
        super(renderer);
    }

    public Value date(Date date) {
        return new Value(date, "'" + DATE_FORMAT.format(date) + "'");
    }
}
