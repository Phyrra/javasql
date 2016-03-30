package ch.sama.sql.dialect.tsql;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.IType;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.dialect.tsql.type.TYPE;
import ch.sama.sql.query.base.check.Identifier;
import ch.sama.sql.query.exception.BadParameterException;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.pattern.Dates;
import ch.sama.sql.query.helper.pattern.Numerics;
import ch.sama.sql.query.standard.ValueFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TSqlValueFactory extends ValueFactory {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final TSqlQueryRenderer renderer = new TSqlQueryRenderer();

    public TSqlValueFactory() {
        super(renderer);
    }

    public Value table(String table) {
        return table(new Table(table));
    }

    public Value table(Table table) {
        return new Value(table, renderer.render(table) + ".*");
    }

    public Value date(Date date) {
        return new Value(date, "CONVERT(datetime, '" + DATE_FORMAT.format(date) + "', 21)");
    }

    @Override
    public Value string(String s) {
        return new Value(s, "'" + s.replace("'", "''") + "'");
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

    public Value cast(Value value, IType type) {
        return new Value(value, "CAST(" + value.getValue() + " AS " + type.getString() + ")");
    }

    @Override
    public Value object(Field field, Object object) {
        if (object == null) {
            return ValueFactory.NULL;
        }

        IType type = field.getDataType();
        if (type == null) {
            throw new BadParameterException("Expected Field with Type: " + field.getName());
        }

        String s = object.toString();

        if (TYPE.isEqualType(type, TYPE.CHAR_TYPE) || TYPE.isEqualType(type, TYPE.VARCHAR_TYPE) || TYPE.isEqualType(type, TYPE.NVARCHAR_TYPE) || TYPE.isEqualType(type, TYPE.TEXT_TYPE)) {
            return string(s);
        }

        if (TYPE.isEqualType(type, TYPE.INT_TYPE)) {
            if (Numerics.isInteger(s)) {
                return numeric(Integer.parseInt(s));
            } else if (Numerics.isNumericalFloat(s)) {
                return numeric((int) Double.parseDouble(s));
            } else {
                throw new BadSqlException("Expected Int, got: " + s + " (" + object.getClass().getSimpleName() + ")");
            }
        }

        if (TYPE.isEqualType(type, TYPE.FLOAT_TYPE)) {
            if (Numerics.isInteger(s)) {
                return numeric(Integer.parseInt(s));
            } else if (Numerics.isNumericalFloat(s)) {
                return numeric(Double.parseDouble(s));
            } else {
                throw new BadSqlException("Expected Double, got: " + s + " (" + object.getClass().getSimpleName() + ")");
            }
        }

        if (TYPE.isEqualType(type, TYPE.BIT_TYPE)) {
            if (s.equals("0") || s.equals("0.0")) {
                return bool(false);
            } else if (s.equals("1") || s.equals("1.0")) {
                return bool(true);
            } else if (s.matches("(?i)true|false")) {
                return bool(Boolean.parseBoolean(s));
            } else {
                throw new BadSqlException("Expected Boolean, got: " + s + " (" + object.getClass().getSimpleName() + ")");
            }
        }

        if (TYPE.isEqualType(type, TYPE.DATETIME_TYPE)) {
            if (Dates.isKnownDate(s)) {
                return function(
                        "CONVERT",
                        type(TYPE.DATETIME_TYPE),
                        string(Dates.toIsoDateTime(s)),
                        numeric(21)
                );
            } else {
                throw new BadSqlException("Expected Date, got: " + s + " (" + object.getClass().getSimpleName() + ")");
            }
        }

        return object(object);
    }

    @Override
    public Value object(Object object) {
        if (object == null) {
            return ValueFactory.NULL;
        }

        if (object instanceof Boolean) {
            return bool((boolean) object);
        }

        if (object instanceof Integer || object instanceof Short || object instanceof Long) {
            if (object instanceof Integer) {
                return numeric((int) object);
            } else if (object instanceof Short) {
                return numeric((int) ((short) object));
            } else {
                return numeric((int) ((long) object));
            }
        }

        if (object instanceof Double || object instanceof Float) {
            if (object instanceof Double) {
                return numeric((double) object);
            } else {
                return numeric((float) object);
            }
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
