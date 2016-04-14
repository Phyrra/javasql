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
}
