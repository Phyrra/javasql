package ch.sama.sql.tsql.dialect;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Function;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.helper.Value;

import java.util.Date;

public class TSqlValueFactory implements IValueFactory {
    @Override
    public Value table(String table) {
        return TSqlValue.plain("[" + table + "].*");
    }

    @Override
    public Value table(Table table) {
        return TSqlValue.plain(table.getString() + ".*");
    }

    @Override
    public Value field(Field field) {
        return new TSqlValue(field);
    }

    @Override
    public Value field(String field) {
        return new TSqlValue(new TSqlField(field));
    }

    @Override
    public Value field(String table, String field) {
        return new TSqlValue(new TSqlField(table, field));
    }

    @Override
    public Value field(Table table, String field) {
        return new TSqlValue(new TSqlField(table, field));
    }

    @Override
    public Value plain(String s) {
        return TSqlValue.plain(s);
    }

    @Override
    public Value date(Date date) {
        return new TSqlValue(date);
    }

    @Override
    public Value string(String s) {
        return new TSqlValue(s);
    }

    @Override
    public Value numeric(Integer i) {
        return new TSqlValue(i);
    }

    @Override
    public Value numeric(Float f) {
        return new TSqlValue(f);
    }

    @Override
    public Value numeric(Double d) {
        return new TSqlValue(d);
    }

    @Override
    public Value function(String fnc) {
        return new TSqlValue(new Function(fnc));
    }

    @Override
    public Value function(Function fnc) {
        return new TSqlValue(fnc);
    }

    @Override
    public Value query(IQuery query) {
        return new TSqlValue(query);
    }

    @Override
    public Value value(Value.VALUE value) {
        return new TSqlValue(value);
    }
}
