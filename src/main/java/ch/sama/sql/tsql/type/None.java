package ch.sama.sql.tsql.type;

import ch.sama.sql.dbo.IType;

public class None implements IType {
    None() { }
    
    @Override
    public String getString() {
        return "none";
    }
}
