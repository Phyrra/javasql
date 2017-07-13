package ch.sama.sql.dialect.tsql.type;

import ch.sama.sql.dbo.IType;

public class SmallIntType implements IType {
    SmallIntType() { }

    @Override
    public String getString() {
        return "smallint";
    }
}