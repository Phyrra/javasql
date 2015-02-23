package ch.sama.sql.tsql.type;

import ch.sama.sql.dbo.IType;

public class DatetimeType implements IType {
    DatetimeType() { }
    
    @Override
    public String getString() {
        return "datetime";
    }
}
