package ch.sama.sql.dialect.tsql.type;

import ch.sama.sql.dbo.IType;
import ch.sama.sql.jpa.Column;
import ch.sama.sql.jpa.Entity;
import ch.sama.sql.query.exception.BadParameterException;
import ch.sama.sql.query.exception.BadSqlException;
import org.junit.Test;

import java.lang.reflect.Field;
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
    public void getIntFromInteger() {
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

    /*
     * Testing with primitive classes
     */

    @Entity
    private static class Table {
        @Column(name = "COLUMN1")
        private double column1;

        @Column(name = "COLUMN2")
        private float column2;

        @Column(name = "COLUMN3")
        private int column3;

        @Column(name = "COLUMN4")
        private short column4;

        @Column(name = "COLUMN5")
        private long column5;

        @Column(name = "COLUMN6")
        private boolean column6;
    }

    private Field getFieldByName(String name) {
        Field[] fields = Table.class.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                String fieldName = field
                        .getAnnotation(Column.class)
                        .name();

                if (fieldName.equalsIgnoreCase(name)) {
                    return field;
                }
            }
        }

        throw new BadParameterException("Field {" + name + "} not found");
    }

    @Test
    public void getFloatFromPrimitiveDouble() {
        Field field = getFieldByName("COLUMN1");

        assertEquals(TYPE.FLOAT_TYPE.getClass(), TYPE.fromClass(field.getType()).getClass());
    }

    @Test
    public void getFloatFromPrimitiveFloat() {
        Field field = getFieldByName("COLUMN2");

        assertEquals(TYPE.FLOAT_TYPE.getClass(), TYPE.fromClass(field.getType()).getClass());
    }

    @Test
    public void getIntFromPrimitiveInt() {
        Field field = getFieldByName("COLUMN3");

        assertEquals(TYPE.INT_TYPE.getClass(), TYPE.fromClass(field.getType()).getClass());
    }

    @Test
    public void getSmallintFromPrimitiveShort() {
        Field field = getFieldByName("COLUMN4");

        assertEquals(TYPE.SMALL_INT_TYPE.getClass(), TYPE.fromClass(field.getType()).getClass());
    }

    @Test
    public void getBigintFromPrimitiveLong() {
        Field field = getFieldByName("COLUMN5");

        assertEquals(TYPE.BIG_INT_TYPE.getClass(), TYPE.fromClass(field.getType()).getClass());
    }

    @Test
    public void getBitFromPrimitiveBoolean() {
        Field field = getFieldByName("COLUMN6");

        assertEquals(TYPE.BIT_TYPE.getClass(), TYPE.fromClass(field.getType()).getClass());
    }
}
