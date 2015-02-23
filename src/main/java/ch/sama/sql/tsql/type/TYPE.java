package ch.sama.sql.tsql.type;

import ch.sama.sql.dbo.IType;
import ch.sama.sql.query.exception.NotImplementedException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TYPE {
    private static final String VARCHAR_PATTERN  = "varchar\\((\\d+|max)\\)";
    private static final String NVARCHAR_PATTERN  = "nvarchar\\((\\d+|max)\\)";
    
    public static final None NO_TYPE = new None();
    
    public static final Uniqueidentifier UNIQUEIDENTIFIER_TYPE = new Uniqueidentifier();
    public static final IntType INT_TYPE = new IntType();
    public static final FloatType FLOAT_TYPE = new FloatType();
    public static final DatetimeType DATETIME_TYPE = new DatetimeType();
    public static final VarcharType VARCHAR_TYPE = new VarcharType();
    public static final VarcharType VARCHAR_MAX_TYPE = new VarcharType(-1);
    public static final NVarcharType NVARCHAR_TYPE = new NVarcharType();
    public static final NVarcharType NVARCHAR_MAX_TYPE = new NVarcharType(-1);
    
    public static VarcharType VARCHAR_TYPE(int max) {
        return new VarcharType(max);
    }
    
    public static NVarcharType NVARCHAR_TYPE(int max) {
        return new NVarcharType(max);
    }
    
    public static IType fromString(String type) {
        String sType = type.toLowerCase();
        
        switch (sType) {
            case "uniqueidentifier":
                return UNIQUEIDENTIFIER_TYPE;
            case "int":
                return INT_TYPE;
            case "float":
                return FLOAT_TYPE;
            case "datetime":
                return DATETIME_TYPE;
            case "varchar":
                return VARCHAR_TYPE;
            case "nvarchar":
                return NVARCHAR_TYPE;
        }

        Matcher varchar = Pattern.compile(VARCHAR_PATTERN).matcher(sType);
        if (varchar.matches()) {
            String length = varchar.group(1);
            
            if ("max".equals(length)) {
                return VARCHAR_MAX_TYPE;
            } else {
                return VARCHAR_TYPE(Integer.parseInt(length));
            }
        }
        
        Matcher nvarchar = Pattern.compile(NVARCHAR_PATTERN).matcher(sType);
        if (nvarchar.matches()) {
            String length = nvarchar.group(1);
            
            if ("max".equals(length)) {
                return NVARCHAR_MAX_TYPE;
            } else {
                return NVARCHAR_TYPE(Integer.parseInt(length));
            }
        }
        
        throw new NotImplementedException("Unknown type: " + type);
    }
}
