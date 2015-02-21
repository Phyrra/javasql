package ch.sama.sql.query.helper.type;

public class TYPE {
    public static final IntType INT_TYPE = new IntType();
    public static final FloatType FLOAT_TYPE = new FloatType();
    public static final DatetimeType DATETIME_TYPE = new DatetimeType();
    public static final VarcharType VARCHAR_TYPE = new VarcharType();
    public static final VarcharType VARCHAR_MAX_TYPE = new VarcharType(-1);
    
    public static VarcharType VARCHAR_TYPE(int max) {
        return new VarcharType(max);
    }
}
