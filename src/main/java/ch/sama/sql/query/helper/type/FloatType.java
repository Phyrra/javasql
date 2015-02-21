package ch.sama.sql.query.helper.type;

public class FloatType implements IType {
    FloatType() { }
    
    @Override
    public String getString(ITypeRenderer renderer) {
        return renderer.render(this);
    }
}
