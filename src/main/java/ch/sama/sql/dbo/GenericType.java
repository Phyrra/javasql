package ch.sama.sql.dbo;

public class GenericType implements IType {
    private String name;
    
    public GenericType(String name) {
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