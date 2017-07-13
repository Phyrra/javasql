package ch.sama.sql.dialect.tsql.type;

import ch.sama.sql.dbo.GenericType;
import ch.sama.sql.dbo.IType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TypeEqualityTest {
    @Test
    public void typeEquality() {
        IType t1 = TYPE.INT_TYPE;
        IType t2 = new GenericType("int");

        assertEquals(false, TYPE.isEqualType(t1, t2));
        assertEquals(true, TYPE.isWeakEqualType(t1, t2));
    }

    @Test
    public void typeEqualityWithLength() {
        IType t1 = TYPE.NVARCHAR_TYPE(10);
        IType t2 = TYPE.NVARCHAR_TYPE(20);

        assertEquals(true, TYPE.isWeakEqualType(t1, t2));
    }
}