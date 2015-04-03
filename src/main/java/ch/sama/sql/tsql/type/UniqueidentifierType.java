package ch.sama.sql.tsql.type;

import ch.sama.sql.dbo.IType;

public class UniqueidentifierType implements IType {
    UniqueidentifierType() { }
    
    @Override
    public String getString() {
        return "uniqueidentifier";
    }
}
