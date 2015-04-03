package ch.sama.sql.tsql.type;

import ch.sama.sql.dbo.IType;

public class IntType implements IType {
    IntType() { }
    
    @Override
    public String getString() {
        return "int";
    }
}
