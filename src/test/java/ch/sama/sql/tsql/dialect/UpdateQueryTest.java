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

public class UpdateQueryTest {
    private static final IQueryFactory fac = new TSqlQueryFactory();
    private static final IValueFactory value = fac.value();
    private static final ISourceFactory source = fac.source();
    
    @Test
    public void updateString() {
        assertEquals(
                "UPDATE [TABLE]",
                fac.query()
                        .update("TABLE")
                .getSql()
        );
    }
    
    @Test
    public void updateTable() {
        assertEquals(
                "UPDATE [dbo].[TABLE]",
                fac.query()
                        .update(new Table("dbo", "TABLE"))
                .getSql()
        );
    }
    
    @Test
    public void setOneStringField() {
        assertEquals(
                "UPDATE [TABLE] SET\n[FIELD] = 1",
                fac.query()
                        .update("TABLE")
                        .set("FIELD", value.numeric(1))
                .getSql()
        );
    }
    
    @Test
    public void setOneField() {
        assertEquals(
                "UPDATE [TABLE] SET\n[FIELD] = 1",
                fac.query()
                        .update("TABLE")
                        .set(new Field("TABLE", "FIELD"), value.numeric(1))
                .getSql()
        );
    }

    @Test
    public void setMultiStringField() {
        assertEquals(
                "UPDATE [TABLE] SET\n[FIELD1] = 1,\n[FIELD2] = 2",
                fac.query()
                        .update("TABLE")
                        .set("FIELD1", value.numeric(1))
                        .set("FIELD2", value.numeric(2))
                .getSql()
        );
    }

    @Test
    public void setMultiField() {
        assertEquals(
                "UPDATE [TABLE] SET\n[FIELD1] = 1,\n[FIELD2] = 2",
                fac.query()
                        .update("TABLE")
                        .set(new Field("TABLE", "FIELD1"), value.numeric(1))
                        .set(new Field("TABLE", "FIELD2"), value.numeric(2))
                .getSql()
        );
    }
    
    @Test
    public void setWithCondition() {
        assertEquals(
                "UPDATE [TABLE] SET\n[FIELD] = 1\nWHERE [ID] = 1",
                fac.query()
                        .update("TABLE")
                        .set("FIELD", value.numeric(1))
                        .where(Condition.eq(value.field("ID"), value.numeric(1)))
                .getSql()
        );
    }

    @Test
    public void updateCte() {
        assertEquals(
                "WITH [CTE] AS (\nSELECT TOP 1 [FIELD1]\nFROM [TABLE1]\n)\nUPDATE [TABLE2] SET\n[FIELD2] = (\nSELECT *\nFROM [CTE]\n)",
                fac.query()
                        .with("CTE").as(
                                fac.query()
                                        .select(value.field("FIELD1")).top(1)
                                        .from(source.table("TABLE1"))
                        )
                        .update("TABLE2")
                        .set(
                                "FIELD2",
                                value.query(
                                        fac.query()
                                                .select(value.value(Value.VALUE.ALL))
                                                .from(source.table("CTE"))
                                )
                        )
                .getSql()
        );
    }
}
