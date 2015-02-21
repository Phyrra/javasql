package ch.sama.sql.query.helper.type;

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
    public String getString(ITypeRenderer renderer) {
        return renderer.render(this);
    }
    
    public boolean hasMaxLength() {
        return hasMaxLength;
    }
    
    public int getMaxLength() {
        return maxLength;
    }
}
