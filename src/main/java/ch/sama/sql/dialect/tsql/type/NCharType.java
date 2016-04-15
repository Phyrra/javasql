package ch.sama.sql.dialect.tsql.type;

import ch.sama.sql.dbo.IType;

public class NCharType implements IType {
    private boolean hasMaxLength;
    private int maxLength;

    NCharType() {
        this.hasMaxLength = false;
    }

    NCharType(int max) {
        this.maxLength = max;
        this.hasMaxLength = true;
    }
    
    @Override
    public String getString() {
        if (hasMaxLength) {
            if (maxLength < 0) {
                return "nchar(MAX)";
            } else {
                return "nchar(" + maxLength  + ")";
            }
        } else {
            return "nchar";
        }
    }
    
    public boolean hasMaxLength() {
        return hasMaxLength;
    }
    
    public int getMaxLength() {
        return maxLength;
    }
}
