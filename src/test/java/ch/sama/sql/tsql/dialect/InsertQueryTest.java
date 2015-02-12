package ch.sama.sql.tsql.dialect;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.IQueryFactory;
import ch.sama.sql.query.base.IValueFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InsertQueryTest {
    private static final IQueryFactory fac = new TSqlQueryFactory();
    private static final IValueFactory value = fac.value();

    @Test
    public void insertIntoString() {
        assertEquals(
                "INSERT INTO [TABLE]",
                fac.query()
                        .insert().into("TABLE")
                .getSql()
        );
    }

    @Test
    public void insertIntoTable() {
        assertEquals(
                "INSERT INTO [TABLE]",
                fac.query()
                        .insert().into(new Table("TABLE"))
                .getSql()
        );
    }
    
    @Test
    public void oneStringField() {
        assertEquals(
                "INSERT INTO [TABLE] ([FIELD])",
                fac.query()
                        .insert().into("TABLE").columns("FIELD")
                .getSql()
        );
    }
    
    @Test
    public void multiStringField() {
        assertEquals(
                "INSERT INTO [TABLE] ([FIELD1], [FIELD2])",
                fac.query()
                        .insert().into("TABLE").columns("FIELD1", "FIELD2")
                .getSql()
        );    
    }

    @Test
    public void oneField() {
        assertEquals(
                "INSERT INTO [TABLE] ([FIELD])",
                fac.query()
                        .insert().into("TABLE").columns(new Field("FIELD"))
                .getSql()
        );
    }

    @Test
    public void multiField() {
        assertEquals(
                "INSERT INTO [TABLE] ([FIELD1], [FIELD2])",
                fac.query()
                        .insert().into("TABLE").columns(new Field("FIELD1"), new Field("FIELD2"))
                .getSql()
        );
    }
    
    @Test
    public void insertValues() {
        assertEquals(
                "INSERT INTO [TABLE] ([FIELD1], [FIELD2])\nSELECT 1, 2",
                fac.query()
                        .insert().into("TABLE").columns("FIELD1", "FIELD2")
                        .select(value.numeric(1), value.numeric(2))
                .getSql()
        );
    }
}
