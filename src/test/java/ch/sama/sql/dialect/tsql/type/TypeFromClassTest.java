package ch.sama.sql.dialect.tsql.type;

import ch.sama.sql.dbo.IType;
import ch.sama.sql.query.exception.BadSqlException;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class TypeFromClassTest {
    @Test
    public void getVarcharMaxFromString() {
        IType type = TYPE.fromClass(String.class);

        assertEquals(TYPE.VARCHAR_MAX_TYPE.getClass(), type.getClass());
        assertEquals(TYPE.VARCHAR_MAX_TYPE.getMaxLength(), ((VarcharType) type).getMaxLength());
    }

    @Test
    public void getFloatFromDouble() {
        assertEquals(TYPE.FLOAT_TYPE.getClass(), TYPE.fromClass(Double.class).getClass());
    }

    @Test
    public void getFloatFromFloat() {
        assertEquals(TYPE.FLOAT_TYPE.getClass(), TYPE.fromClass(Float.class).getClass());
    }

    @Test
    public void getIntFromInt() {
        assertEquals(TYPE.INT_TYPE.getClass(), TYPE.fromClass(Integer.class).getClass());
    }

    @Test
    public void getSmallintFromShort() {
        assertEquals(TYPE.SMALL_INT_TYPE.getClass(), TYPE.fromClass(Short.class).getClass());
    }

    @Test
    public void getBigintFromLong() {
        assertEquals(TYPE.BIG_INT_TYPE.getClass(), TYPE.fromClass(Long.class).getClass());
    }

    @Test
    public void getBitFromBoolean() {
        assertEquals(TYPE.BIT_TYPE.getClass(), TYPE.fromClass(Boolean.class).getClass());
    }

    @Test
    public void getDatetimeFromDate() {
        assertEquals(TYPE.DATETIME_TYPE.getClass(), TYPE.fromClass(Date.class).getClass());
    }

    @Test
    public void getUniqueidentifierFromUuid() {
        assertEquals(TYPE.UNIQUEIDENTIFIER_TYPE.getClass(), TYPE.fromClass(UUID.class).getClass());
    }

    @Test(expected = BadSqlException.class)
    public void getErrorFromObject() {
        TYPE.fromClass(Object.class);
    }
}
