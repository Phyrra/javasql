package ch.sama.sql.tsql.type;

import ch.sama.sql.dbo.IType;

public class NVarcharType implements IType {
    private boolean hasMaxLength;
    private int maxLength;

    NVarcharType() {
        this.hasMaxLength = false;
    }

    NVarcharType(int max) {
        this.maxLength = max;
        this.hasMaxLength = true;
    }
    
    @Override
    public String getString() {
        if (hasMaxLength) {
            if (maxLength < 0) {
                return "nvarchar(MAX)";
            } else {
                return "nvarchar(" + maxLength  + ")";
            }
        } else {
            return "nvarchar";
        }
    }
    
    public boolean hasMaxLength() {
        return hasMaxLength;
    }
    
    public int getMaxLength() {
        return maxLength;
    }
}
