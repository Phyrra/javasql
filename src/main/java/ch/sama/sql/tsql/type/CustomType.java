package ch.sama.sql.tsql.type;

import ch.sama.sql.dbo.IType;

public class CustomType implements IType {
    private String name;
    
    CustomType(String name) {
        this.name = name;    
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String getString() {
        return name;
    }
}
