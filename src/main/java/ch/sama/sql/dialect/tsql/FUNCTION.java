package ch.sama.sql.dialect.tsql;

import ch.sama.sql.query.helper.Value;

public enum FUNCTION {
    COALESCE("COALESCE", 2),
    CONVERT("CONVERT", 2),
    GETUTCDATE("GETUTCDATE", 0),
    UPPER("UPPER", 1),
    LOWER("LOWER", 1);

    private static final TSqlValueFactory fac = new TSqlValueFactory();

    private String name;
    private int numArgs;

    FUNCTION(String name, int numArgs) {
        this.name = name;
        this.numArgs = numArgs;
    }

    public String getName() {
        return name;
    }

    public int getNumArgs() {
        return numArgs;
    }

    public Value getValue(Value... args) {
        return fac.function(this, args);
    }
}
