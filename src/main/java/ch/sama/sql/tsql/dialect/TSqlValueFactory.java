package ch.sama.sql.tsql.dialect;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Function;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.exception.UnknownValueException;
import ch.sama.sql.query.helper.Value;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TSqlValueFactory implements IValueFactory {
    private static final IQueryRenderer renderer = new TSqlQueryRenderer();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Value table(String table) {
        return table(new Table(table));
    }

    @Override
    public Value table(Table table) {
        return new Value(table, table.getString(renderer) + ".*");
    }

    @Override
    public Value field(Field field) {
        return new Value(field, field.getString(renderer));
    }

    @Override
    public Value field(String field) {
        return field(new Field(field));
    }

    @Override
    public Value field(String table, String field) {
        return field(new Field(table, field));
    }

    @Override
    public Value field(Table table, String field) {
        return field(new Field(table, field));
    }

    @Override
    public Value plain(String s) {
        return new Value(null, s);
    }

    @Override
    public Value date(Date date) {
        return new Value(date, "CONVERT(datetime, '" + dateFormat.format(date) + "', 21)");
    }

    @Override
    public Value string(String s) {
        return new Value(s, "'" + s.replace("'", "''") + "'");
    }

    @Override
    public Value numeric(Integer i) {
        return new Value(i, i.toString());
    }

    @Override
    public Value numeric(Float f) {
        return new Value(f, f.toString());
    }

    @Override
    public Value numeric(Double d) {
        return new Value(d, d.toString());
    }

    @Override
    public Value function(String fnc) {
        return function(new Function(fnc));
    }

    @Override
    public Value function(Function fnc) {
        return new Value(fnc, fnc.getString());
    }

    @Override
    public Value query(IQuery query) {
        return new Value(query, "(\n" + query.getSql(renderer) + "\n)");
    }

    @Override
    public Value value(Value.VALUE value) {
        switch (value) {
            case NULL:
                return new Value(value, "NULL");
            case ALL:
                return new Value(value, "*");
            default:
                throw new UnknownValueException("Caused by: " + value);
        }
    }
}