package ch.sama.sql.tsql.type;

import ch.sama.sql.dbo.IType;

public class Uniqueidentifier implements IType {
    Uniqueidentifier() { }
    
    @Override
    public String getString() {
        return "uniqueidentifier";
    }
}
