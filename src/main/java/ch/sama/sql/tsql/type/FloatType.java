package ch.sama.sql.tsql.type;

import ch.sama.sql.dbo.IType;

public class FloatType implements IType {
    FloatType() { }
    
    @Override
    public String getString() {
        return "float";
    }
}
