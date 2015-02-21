package ch.sama.sql.query.helper.type;

public class IntType implements IType {
    IntType() { }
    
    @Override
    public String getString(ITypeRenderer renderer) {
        return renderer.render(this);
    }
}
