package ch.sama.sql.query.helper.type;

public interface ITypeRenderer {
    public String render(VarcharType type);
    public String render(IntType type);
    public String render(FloatType type);
    public String render(DatetimeType type);
}
