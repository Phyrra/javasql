package ch.sama.sql.query.base;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.query.helper.Function;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.dbo.IType;

import java.util.Date;

public interface IValueFactory {
    public Value table(String table);
    public Value table(Table table);

    public Value field(Field field);
    public Value field(String field);
    public Value field(String table, String field);
    public Value field(Table table, String field);

    public Value plain(String s);
    public Value date(Date date);
    public Value string(String s);
    public Value numeric(Integer i);
    public Value numeric(Float f);
    public Value numeric(Double d);
    public Value function(String fnc, Value... parameters);
    public Value function(Function fnc);
    public Value query(IQuery query);
    public Value value(Value.VALUE val);
    
    public Value combine(String operator, Value... values);
    public Value bracket(Value value);
    
    public Value type(IType type);
}
