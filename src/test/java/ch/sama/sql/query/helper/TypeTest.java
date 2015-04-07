package ch.sama.sql.query.helper;

import ch.sama.sql.dbo.GenericType;
import ch.sama.sql.dbo.IType;
import ch.sama.sql.dialect.tsql.type.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TypeTest {
    @Test
    public void uniqueidentifier() {
        assertEquals("uniqueidentifier", TYPE.UNIQUEIDENTIFIER_TYPE.getString());    
    }
    
    @Test
    public void bitType() {
        assertEquals("bit", TYPE.BIT_TYPE.getString());
    }
    
    @Test
    public void intType() {
        assertEquals("int", TYPE.INT_TYPE.getString());
    }
    
    @Test
    public void floatType() {
        assertEquals("float", TYPE.FLOAT_TYPE.getString());
    }
    
    @Test
    public void datetimeType() {
        assertEquals("datetime", TYPE.DATETIME_TYPE.getString());
    }

    @Test
    public void charNoMax() {
        assertEquals("char", TYPE.CHAR_TYPE.getString());
    }

    @Test
    public void charMax() {
        assertEquals("char(MAX)", TYPE.CHAR_MAX_TYPE.getString());
    }

    @Test
    public void charIntMax() {
        assertEquals("char(64)", TYPE.CHAR_TYPE(64).getString());
    }
    
    @Test
    public void varcharNoMax() {
        assertEquals("varchar", TYPE.VARCHAR_TYPE.getString());
    }
    
    @Test
    public void varcharMax() {
        assertEquals("varchar(MAX)", TYPE.VARCHAR_MAX_TYPE.getString());
    }
    
    @Test
    public void varcharIntMax() {
        assertEquals("varchar(64)", TYPE.VARCHAR_TYPE(64).getString());
    }

    @Test
    public void nvarcharNoMax() {
        assertEquals("nvarchar", TYPE.NVARCHAR_TYPE.getString());
    }

    @Test
    public void nvarcharMax() {
        assertEquals("nvarchar(MAX)", TYPE.NVARCHAR_MAX_TYPE.getString());
    }

    @Test
    public void nvarcharIntMax() {
        assertEquals("nvarchar(64)", TYPE.NVARCHAR_TYPE(64).getString());
    }
    
    @Test
    public void none() {
        assertEquals("none", TYPE.NO_TYPE.getString());
    }
    
    @Test
    public void custom() {
        assertEquals("custom", TYPE.CUSTOM_TYPE("custom").getString());
    }
    
    @Test
    public void text() {
        assertEquals("text", TYPE.TEXT_TYPE.getString());
    }
    
    @Test
    public void uniqueidentifierFromString() {
        assertEquals(UniqueidentifierType.class, TYPE.fromString("uniqueidentifier").getClass());
    }
    
    @Test
    public void bitFromString() {
        assertEquals(BitType.class, TYPE.fromString("bit").getClass());
    }
    
    @Test
    public void intFromString() {
        assertEquals(IntType.class, TYPE.fromString("int").getClass());
    }
    
    @Test
    public void floatFromString() {
        assertEquals(FloatType.class, TYPE.fromString("float").getClass());
    }
    
    @Test
    public void datetimeFromString() {
        assertEquals(DatetimeType.class, TYPE.fromString("datetime").getClass());
    }
    
    @Test
    public void charFromString() {
        assertEquals(CharType.class, TYPE.fromString("char").getClass());
    }
    
    @Test
    public void charNFromString() {
        IType type = TYPE.fromString("char(10)");
        
        assertEquals(CharType.class, type.getClass());
        
        CharType cType = (CharType) type;
        
        assertEquals(true, cType.hasMaxLength());
        assertEquals(10, cType.getMaxLength());
    }
    
    @Test
    public void charMaxFromString() {
        IType type = TYPE.fromString("char(MaX)");

        assertEquals(CharType.class, type.getClass());

        CharType cType = (CharType) type;

        assertEquals(true, cType.hasMaxLength());
        assertEquals(-1, cType.getMaxLength());
    }

    @Test
    public void varcharFromString() {
        assertEquals(VarcharType.class, TYPE.fromString("varchar").getClass());
    }

    @Test
    public void varcharNFromString() {
        IType type = TYPE.fromString("varchar(10)");

        assertEquals(VarcharType.class, type.getClass());

        VarcharType vcType = (VarcharType) type;

        assertEquals(true, vcType.hasMaxLength());
        assertEquals(10, vcType.getMaxLength());
    }

    @Test
    public void varcharMaxFromString() {
        IType type = TYPE.fromString("varchar(MaX)");

        assertEquals(VarcharType.class, type.getClass());

        VarcharType vcType = (VarcharType) type;

        assertEquals(true, vcType.hasMaxLength());
        assertEquals(-1, vcType.getMaxLength());
    }

    @Test
    public void nvarcharFromString() {
        assertEquals(NVarcharType.class, TYPE.fromString("nvarchar").getClass());
    }

    @Test
    public void nvarcharNFromString() {
        IType type = TYPE.fromString("nvarchar(10)");

        assertEquals(NVarcharType.class, type.getClass());

        NVarcharType nvcType = (NVarcharType) type;

        assertEquals(true, nvcType.hasMaxLength());
        assertEquals(10, nvcType.getMaxLength());
    }

    @Test
    public void nvarcharMaxFromString() {
        IType type = TYPE.fromString("nvarchar(MaX)");

        assertEquals(NVarcharType.class, type.getClass());

        NVarcharType nvcType = (NVarcharType) type;

        assertEquals(true, nvcType.hasMaxLength());
        assertEquals(-1, nvcType.getMaxLength());
    }
    
    @Test
    public void textFromString() {
        assertEquals(TextType.class, TYPE.fromString("text").getClass());
    }
    
    @Test
    public void testNFromString() {
        assertEquals(TextType.class, TYPE.fromString("text(2147483647)").getClass());
    }
    
    @Test
    public void noType() {
        IType type = TYPE.fromString("none");
        
        assertEquals(GenericType.class, type.getClass());
        
        GenericType cType = (GenericType) type;
        
        assertEquals("none", cType.getName());
    }
}
