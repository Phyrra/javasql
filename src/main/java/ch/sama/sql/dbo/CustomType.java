package ch.sama.sql.dbo;

public class CustomType implements IType {
    private String name;
    
    public CustomType(String name) {
        this.name = name;    
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String getString() {
        return name;
    }
}