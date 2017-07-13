package ch.sama.sql.dialect.tsql.type;

import org.junit.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class TypeToClassTest {
    @Test
    public void getBooleanFromBit() {
        assertEquals(Boolean.class, TYPE.toClass(TYPE.BIT_TYPE));
    }

    @Test
    public void getStringFromChar() {
        assertEquals(String.class, TYPE.toClass(TYPE.CHAR_TYPE));
    }

    @Test
    public void getStringFromCharWithLength() {
        assertEquals(String.class, TYPE.toClass(TYPE.CHAR_TYPE(100)));
    }

    @Test
    public void getStringFromNChar() {
        assertEquals(String.class, TYPE.toClass(TYPE.NCHAR_TYPE));
    }

    @Test
    public void getStringFromNCharWithLength() {
        assertEquals(String.class, TYPE.toClass(TYPE.NCHAR_TYPE(100)));
    }

    @Test
    public void getStringFromVarchar() {
        assertEquals(String.class, TYPE.toClass(TYPE.VARCHAR_TYPE));
    }

    @Test
    public void getStringFromVarcharWithLength() {
        assertEquals(String.class, TYPE.toClass(TYPE.VARCHAR_TYPE(100)));
    }

    @Test
    public void getStringFromNVarchar() {
        assertEquals(String.class, TYPE.toClass(TYPE.NVARCHAR_TYPE));
    }

    @Test
    public void getStringFromBVarcharWithLength() {
        assertEquals(String.class, TYPE.toClass(TYPE.NVARCHAR_TYPE(100)));
    }

    @Test
    public void getStringFromText() {
        assertEquals(String.class, TYPE.toClass(TYPE.TEXT_TYPE));
    }

    @Test
    public void getDoubleFromFloat() {
        assertEquals(Double.class, TYPE.toClass(TYPE.FLOAT_TYPE));
    }

    @Test
    public void getIntFromInt() {
        assertEquals(Integer.class, TYPE.toClass(TYPE.INT_TYPE));
    }

    @Test
    public void getShortFromSmallint() {
        assertEquals(Short.class, TYPE.toClass(TYPE.SMALL_INT_TYPE));
    }

    @Test
    public void getShortFromBigint() {
        assertEquals(Long.class, TYPE.toClass(TYPE.BIG_INT_TYPE));
    }

    @Test
    public void getDateFromDatetime() {
        assertEquals(Date.class, TYPE.toClass(TYPE.DATETIME_TYPE));
    }

    @Test
    public void getUuidFromUniqueidentifier() {
        assertEquals(UUID.class, TYPE.toClass(TYPE.UNIQUEIDENTIFIER_TYPE));
    }
}
