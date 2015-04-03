package ch.sama.sql.query.generic;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.IType;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.exception.NotImplementedException;
import ch.sama.sql.query.exception.UnknownValueException;
import ch.sama.sql.query.helper.Function;
import ch.sama.sql.query.helper.Value;

import java.util.Date;

public abstract class ValueFactory implements IValueFactory {
    private IQueryRenderer renderer;

    public ValueFactory(IQueryRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public Value table(Table table) {
        return new Value(table, table.getString(renderer) + ".*");
    }

    @Override
    public Value table(String table) {
        return table(new Table(table));
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
        return new Value(s);
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
    public Value function(String fnc, Value... parameters) {
        return function(new Function(fnc, parameters));
    }

    @Override
    public Value function(Function fnc) {
        return new Value(fnc, fnc.getString(renderer));
    }

    @Override
    public Value query(IQuery query) {
        return new Value(query, "(\n" + query.getSql() + "\n)");
    }

    @Override
    public Value combine(String operator, Value... values) {
        StringBuilder builder = new StringBuilder();
        String prefix = "";

        for (Value v : values) {
            builder.append(prefix);
            builder.append(v.getValue());

            prefix = " " + operator + " ";
        }

        return new Value(builder.toString());
    }

    @Override
    public Value bracket(Value value) {
        return new Value("(" + value.getValue() + ")");
    }

    @Override
    public Value type(IType type) {
        return new Value(type, type.getString());
    }
}
