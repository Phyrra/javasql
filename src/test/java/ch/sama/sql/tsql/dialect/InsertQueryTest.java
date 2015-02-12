package ch.sama.sql.tsql.dialect;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.IQueryFactory;
import ch.sama.sql.query.base.ISourceFactory;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Value;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InsertQueryTest {
    private static final IQueryFactory fac = new TSqlQueryFactory();
    private static final IValueFactory value = fac.value();
    private static final ISourceFactory source = fac.source();

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
                "INSERT INTO [dbo].[TABLE]",
                fac.query()
                        .insert().into(new Table("dbo", "TABLE"))
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
                        .insert().into("TABLE").columns(new Field("TABLE", "FIELD"))
                .getSql()
        );
    }

    @Test
    public void multiField() {
        assertEquals(
                "INSERT INTO [TABLE] ([FIELD1], [FIELD2])",
                fac.query()
                        .insert().into("TABLE").columns(new Field("TABLE", "FIELD1"), new Field("TABLE", "FIELD2"))
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
    
    @Test
    public void insertCte() {
        assertEquals(
                "WITH [CTE] AS (\nSELECT [FIELD1], [FIELD2]\nFROM [TABLE1]\n)\nINSERT INTO [TABLE2] ([F1], [F2])\nSELECT *\nFROM [CTE]",
                fac.query()
                        .with("CTE").as(
                                fac.query()
                                        .select(value.field("FIELD1"), value.field("FIELD2"))
                                        .from(source.table("TABLE1"))
                        )
                        .insert()
                                .into(new Table("TABLE2"))
                                .columns("F1", "F2")
                        .select(value.value(Value.VALUE.ALL))
                        .from(source.table("CTE"))
                .getSql()
        );
    }
}
