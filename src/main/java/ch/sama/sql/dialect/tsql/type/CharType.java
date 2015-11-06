package ch.sama.sql.dialect.tsql.type;

import ch.sama.sql.dbo.IType;

public class CharType implements IType {
    private boolean hasMaxLength;
    private int maxLength;

    CharType() {
        this.hasMaxLength = false;
    }

    CharType(int max) {
        this.maxLength = max;
        this.hasMaxLength = true;
    }
    
    @Override
    public String getString() {
        if (hasMaxLength) {
            if (maxLength < 0) {
                return "char(MAX)";
            } else {
                return "char(" + maxLength  + ")";
            }
        } else {
            return "char";
        }
    }
    
    public boolean hasMaxLength() {
        return hasMaxLength;
    }
    
    public int getMaxLength() {
        return maxLength;
    }
}
