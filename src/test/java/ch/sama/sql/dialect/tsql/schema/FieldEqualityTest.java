package ch.sama.sql.dialect.tsql.schema;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.GenericType;
import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import ch.sama.sql.query.helper.Value;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FieldEqualityTest {
    private static final TSqlValueFactory value = new TSqlValueFactory();

    @Test
    public void differentName() {
        Field f1 = new Field("field1");
        Field f2 = new Field("field2");

        assertEquals(false, f1.compareTo(f2));
    }

    @Test
    public void differentNullability() {
        Field f1 = new Field("field").chainNullable(true);
        Field f2 = new Field("field").chainNullable(false);

        assertEquals(false, f1.compareTo(f2));
    }

    @Test
    public void differentAutoIncrement() {
        Field f1 = new Field("field").chainAutoIncrement(true);
        Field f2 = new Field("field").chainAutoIncrement(false);

        assertEquals(false, f1.compareTo(f2));
    }

    @Test
    public void differentTypeOtherNull() {
        Field f1 = new Field("field").chainType(new GenericType("int"));
        Field f2 = new Field("field");

        assertEquals(false, f1.compareTo(f2));
    }

    @Test
    public void differentTypeThisNull() {
        Field f1 = new Field("field");
        Field f2 = new Field("field").chainType(new GenericType("int"));

        assertEquals(false, f1.compareTo(f2));
    }

    @Test
    public void differentTypes() {
        Field f1 = new Field("field").chainType(new GenericType("float"));
        Field f2 = new Field("field").chainType(new GenericType("int"));

        assertEquals(false, f1.compareTo(f2));
    }

    @Test
    public void differentDefaultOtherNull() {
        Field f1 = new Field("field").chainDefaultValue(value.string("default"));
        Field f2 = new Field("field");

        assertEquals(false, f1.compareTo(f2));
    }

    @Test
    public void differentDefaultThisNull() {
        Field f1 = new Field("field");
        Field f2 = new Field("field").chainDefaultValue(value.string("default"));

        assertEquals(false, f1.compareTo(f2));
    }

    @Test
    public void differentDefaults() {
        Field f1 = new Field("field").chainDefaultValue(value.string("default1"));
        Field f2 = new Field("field").chainDefaultValue(value.string("default2"));

        assertEquals(false, f1.compareTo(f2));
    }

    @Test
    public void noDifference() {
        Field f1 = new Field("field")
                .chainAutoIncrement(true)
                .chainNullable(true)
                .chainType(new GenericType("int"))
                .chainDefaultValue(value.numeric(1));

        Field f2 = new Field("field")
                .chainAutoIncrement(true)
                .chainNullable(true)
                .chainType(new GenericType("int"))
                .chainDefaultValue(value.numeric(1));

        assertEquals(true, f1.compareTo(f2));
    }
}
