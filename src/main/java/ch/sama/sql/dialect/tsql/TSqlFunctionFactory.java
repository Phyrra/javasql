package ch.sama.sql.dialect.tsql;

import ch.sama.sql.dbo.Function;
import ch.sama.sql.query.helper.Value;

/*
    There is no parent object to this,
    the function factory is not necessary - only a convenient helper

    The functions are much too dialect specific and cannot easily be abstracted
 */
public class TSqlFunctionFactory {
    public TSqlFunctionFactory() { }
    
    public Function coalesce(Value lhs, Value rhs) {
        return new Function("COALESCE(" + lhs.toString() + ", " + rhs.toString() + ")");
    }

    // extend at leisure
}
