package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.generic.ValueFactory;
import ch.sama.sql.query.exception.UnknownValueException;
import ch.sama.sql.query.helper.Value;

import java.text.SimpleDateFormat;
import java.util.Date;

class TSqlValueFactory extends ValueFactory {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public TSqlValueFactory() {
        super(new TSqlQueryRenderer());
    }

    @Override
    public Value date(Date date) {
        return new Value(date, "CONVERT(datetime, '" + DATE_FORMAT.format(date) + "', 21)");
    }

    @Override
    public Value string(String s) {
        return new Value(s, "'" + s.replace("'", "''") + "'");
    }
    
    @Override
    public Value bool(Boolean b) {
        if (b) {
            return new Value(true, "1");
        } else {
            return new Value(false, "0");
        }
    }

    @Override
    public Value value(Value.VALUE value) {
        switch (value) {
            case NULL:
                return new Value(value, "NULL");
            case ALL:
                return new Value(value, "*");
            default:
                throw new UnknownValueException("Unknown value: " + value);
        }
    }
}
