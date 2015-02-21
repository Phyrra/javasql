package ch.sama.sql.query.helper.type;

public class DatetimeType implements IType {
    DatetimeType() { }
    
    @Override
    public String getString(ITypeRenderer renderer) {
        return renderer.render(this);
    }
}
