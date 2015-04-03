package ch.sama.sql.tsql.type;

import ch.sama.sql.dbo.IType;

public class TextType implements IType {
    TextType() { }
    
    @Override
    public String getString() {
        return "text";
    }
}
