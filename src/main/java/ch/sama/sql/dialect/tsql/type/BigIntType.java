package ch.sama.sql.dialect.tsql.type;

import ch.sama.sql.dbo.IType;

public class BigIntType implements IType {
    BigIntType() { }

    @Override
    public String getString() {
        return "bigint";
    }
}