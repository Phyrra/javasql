package ch.sama.sql.tsql.type;

import ch.sama.sql.dbo.IType;

public class BitType implements IType {
    BitType() { }
    
    @Override
    public String getString() {
        return "bit";
    }
}
