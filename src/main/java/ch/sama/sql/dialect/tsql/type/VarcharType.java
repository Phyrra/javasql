package ch.sama.sql.dialect.tsql.type;

import ch.sama.sql.dbo.IType;

public class VarcharType implements IType {
    private boolean hasMaxLength;
    private int maxLength;
    
    VarcharType() {
        this.hasMaxLength = false;
    }
    
    VarcharType(int max) {
        this.maxLength = max;
        this.hasMaxLength = true;
    }
    
    @Override
    public String getString() {
        if (hasMaxLength) {
            if (maxLength < 0) {
                return "varchar(MAX)";
            } else {
                return "varchar(" + maxLength  + ")";
            }
        } else {
            return "varchar";
        }
    }
    
    public boolean hasMaxLength() {
        return hasMaxLength;
    }
    
    public int getMaxLength() {
        return maxLength;
    }
}
