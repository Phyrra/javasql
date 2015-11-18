package ch.sama.sql.dialect.tsql.type;

import ch.sama.sql.dbo.GenericType;
import ch.sama.sql.dbo.IType;
import ch.sama.sql.query.exception.BadSqlException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TYPE {
    private static final String CHAR_PATTERN  = "char\\((\\d+|max)\\)";
    private static final String VARCHAR_PATTERN  = "varchar\\((\\d+|max)\\)";
    private static final String NVARCHAR_PATTERN  = "nvarchar\\((\\d+|max)\\)";
    private static final String TEXT_PATTERN = "text\\(\\d+\\)";
    
    public static final UniqueidentifierType UNIQUEIDENTIFIER_TYPE = new UniqueidentifierType();
    public static final BitType BIT_TYPE = new BitType();
    public static final IntType INT_TYPE = new IntType();
    public static final FloatType FLOAT_TYPE = new FloatType();
    public static final DatetimeType DATETIME_TYPE = new DatetimeType();
    public static final CharType CHAR_TYPE = new CharType();
    public static final CharType CHAR_MAX_TYPE = new CharType(-1);
    public static final VarcharType VARCHAR_TYPE = new VarcharType();
    public static final VarcharType VARCHAR_MAX_TYPE = new VarcharType(-1);
    public static final NVarcharType NVARCHAR_TYPE = new NVarcharType();
    public static final NVarcharType NVARCHAR_MAX_TYPE = new NVarcharType(-1);
    public static final TextType TEXT_TYPE = new TextType();
    public static final GenericType NO_TYPE = new GenericType("none");

    public static CharType CHAR_TYPE(int max) {
        return new CharType(max);
    }
    
    public static VarcharType VARCHAR_TYPE(int max) {
        return new VarcharType(max);
    }
    
    public static NVarcharType NVARCHAR_TYPE(int max) {
        return new NVarcharType(max);
    }
    
    public static GenericType CUSTOM_TYPE(String name) {
        return new GenericType(name);
    }
    
    public static IType fromString(String type) {
        String sType = type.toLowerCase();
        
        switch (sType) {
            case "uniqueidentifier":
                return UNIQUEIDENTIFIER_TYPE;
            case "bit":
                return BIT_TYPE;
            case "int":
                return INT_TYPE;
            case "float":
                return FLOAT_TYPE;
            case "datetime":
                return DATETIME_TYPE;
            case "char":
                return CHAR_TYPE;
            case "varchar":
                return VARCHAR_TYPE;
            case "nvarchar":
                return NVARCHAR_TYPE;
            case "text":
                return TEXT_TYPE;
        }

        Matcher charP = Pattern.compile(CHAR_PATTERN).matcher(sType);
        if (charP.matches()) {
            String length = charP.group(1);

            if ("max".equals(length)) {
                return CHAR_MAX_TYPE;
            } else {
                return CHAR_TYPE(Integer.parseInt(length));
            }
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
        
        Matcher text = Pattern.compile(TEXT_PATTERN).matcher(sType);
        if (text.matches()) {
            return TEXT_TYPE;
        }
        
        return CUSTOM_TYPE(type);
    }
    
    public static boolean isEqualType(IType t1, IType t2) {
        if (t1 instanceof GenericType && t2 instanceof GenericType) {
            return t1.getString().equalsIgnoreCase(t2.getString());
        }

        return t1.getClass().equals(t2.getClass());
    }

    public static boolean isWeakEqualType(IType t1, IType t2) {
        String s1 = t1.getString();

        int i1 = s1.indexOf("(");
        if (i1 != -1) {
            s1 = s1.substring(0, i1);
        }

        String s2 = t2.getString();

        int i2 = s2.indexOf("(");
        if (i2 != -1) {
            s2 = s2.substring(0, i2);
        }

        return s1.equalsIgnoreCase(s2);
    }

    public static IType fromClass(Class clazz) {
        String name = clazz.getSimpleName().toLowerCase();

        switch (name) {
            case "string":
                return VARCHAR_MAX_TYPE;
            case "double":
            case "float":
                return FLOAT_TYPE;
            case "int":
            case "short":
            case "long":
                return INT_TYPE;
            case "boolean":
                return BIT_TYPE;
            case "date":
                return DATETIME_TYPE;
        }

        throw new BadSqlException("Class cannot be converted to type");
    }
}
