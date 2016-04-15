package ch.sama.sql.dialect.tsql;

import ch.sama.sql.dbo.IType;
import ch.sama.sql.query.base.check.Identifier;
import ch.sama.sql.query.exception.BadParameterException;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.query.helper.Function;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.standard.ValueFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TSqlValueFactory extends ValueFactory {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final TSqlQueryRenderer renderer = new TSqlQueryRenderer();

    public TSqlValueFactory() {
        super(renderer);
    }

    @Override
    public Value string(String s) {
        return new Value(s, "'" + s.replace("'", "''") + "'");
    }
    
    public Value date(Date date) {
        return new Value(date, "CONVERT(datetime, '" + DATE_FORMAT.format(date) + "', 21)");
    }

    public Value variable(String name) {
        if (!Identifier.test(name)) {
            throw new IllegalIdentifierException(name);
        }

        return new Value(name, "@" + name);
    }

    public Value function(FUNCTION fnc, Value... arguments) {
        if (arguments.length != fnc.getNumArgs()) {
            throw new BadParameterException(fnc.getName() + " expected " + fnc.getNumArgs() + ", but got " + arguments.length);
        }

        return function(fnc.getName(), arguments);
    }

    public Value udf(String schema, String name, Value... arguments) {
        if (!Identifier.test(schema)) {
            throw new IllegalIdentifierException("Illegal schema name: " + schema);
        }

        Function function = new Function(name, arguments);
        return new Value(function, renderer.renderObjectName(schema) + "." + renderer.render(function));
    }

    public Value cast(Value value, IType type) {
        return new Value(value, "CAST(" + value.getValue() + " AS " + type.getString() + ")");
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
            return numeric((int) (long) object);
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
