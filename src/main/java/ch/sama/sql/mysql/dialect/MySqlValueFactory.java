package ch.sama.sql.mysql.dialect;

import ch.sama.sql.query.exception.NotImplementedException;
import ch.sama.sql.query.exception.UnknownValueException;
import ch.sama.sql.query.generic.ValueFactory;
import ch.sama.sql.query.helper.Value;

import java.util.Date;

public class MySqlValueFactory extends ValueFactory {
    public MySqlValueFactory() {
        super(new MySqlQueryRenderer());
    }

    @Override
    public Value date(Date date) {
        throw new NotImplementedException("I have no idea how Dates work in MySql :)");
    }

    @Override
    public Value string(String s) {
        return new Value(s, "'" + s.replace("'", "\\'") + "'");
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
