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
        return new Value(date, "CONVERT(datetime, '" + DATE_FORMAT.format(date) + "', 21)");
    }

    @Override
    public Value object(Object object) {
        if (object == null) {
            return ValueFactory.NULL;
        }

        if (object instanceof Boolean) {
            return bool((boolean) object);
        }

        if (object instanceof Integer) {
            return numeric((int) object);
        }

        if (object instanceof Short) {
            return numeric((int) (short) object);
        }

        if (object instanceof Long) {
            return numeric((long) object);
        }

        if (object instanceof Double) {
            return numeric((double) object);
        }

        if (object instanceof Float) {
            return numeric((float) object);
        }

        if (object instanceof Date) {
            return date((Date) object);
        }

        if (object instanceof UUID) {
            return string(object.toString());
        }

        if (object instanceof String) {
            String s = (String) object;

            // Bit of a gamble..
            // empty strings will be interpreted as null
            if (s.length() == 0 || s.toLowerCase().equals("null")) {
                return ValueFactory.NULL;
            }

            return string(s);
        }

        throw new BadParameterException("Cannot guess {" + object.getClass().getName() + ": " + object.toString() + "}");
    }
}
