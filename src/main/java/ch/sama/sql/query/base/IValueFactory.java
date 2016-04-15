package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.IType;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.helper.Function;
import ch.sama.sql.query.helper.Value;

public interface IValueFactory {
    public Value plain(String s);

    public Value field(Field field);
    public Value field(String field);
    public Value field(String table, String field);
    public Value field(String table, Field field);
    public Value field(Table table, String field);
    public Value field(Table table, Field field);

    public Value table(Table table);
    public Value table(String table);
    public Value table(String schema, String table);
    public Value table(String schema, Table table);

    public Value function(String fnc, Value... arguments);
    public Value function(Function fnc);

    public Value query(IQuery query);

    public Value type(IType type);

    public Value string(String s);
    public Value numeric(Integer i);
    public Value numeric(Float f);
    public Value numeric(Double d);
    public Value bool(Boolean b);
    
    public Value combine(String operator, Value... values);
    public Value bracket(Value value);

    public Value object(Object o);
}
