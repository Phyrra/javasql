package ch.sama.sql.tsql.dialect;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.IQueryFactory;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.helper.Condition;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UpdateQueryTest {
    private static final IQueryFactory fac = new TSqlQueryFactory();
    private static final IValueFactory value = fac.value();
    
    @Test
    public void updateString() {
        assertEquals(
                "UPDATE [TABLE] SET (\n)",
                fac.query()
                        .update("TABLE")
                .getSql()
        );
    }
    
    @Test
    public void updateTable() {
        assertEquals(
                "UPDATE [dbo].[TABLE] SET (\n)",
                fac.query()
                        .update(new Table("dbo", "TABLE"))
                .getSql()
        );
    }
    
    @Test
    public void setOneStringField() {
        assertEquals(
                "UPDATE [TABLE] SET (\n[FIELD] = 1\n)",
                fac.query()
                        .update("TABLE")
                        .set("FIELD", value.numeric(1))
                .getSql()
        );
    }
    
    @Test
    public void setOneField() {
        assertEquals(
                "UPDATE [TABLE] SET (\n[FIELD] = 1\n)",
                fac.query()
                        .update("TABLE")
                        .set(new Field("TABLE", "FIELD"), value.numeric(1))
                .getSql()
        );
    }

    @Test
    public void setMultiStringField() {
        assertEquals(
                "UPDATE [TABLE] SET (\n[FIELD1] = 1,\n[FIELD2] = 2\n)",
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
                "UPDATE [TABLE] SET (\n[FIELD1] = 1,\n[FIELD2] = 2\n)",
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
                "UPDATE [TABLE] SET (\n[FIELD] = 1\n)\nWHERE [ID] = 1",
                fac.query()
                        .update("TABLE")
                        .set("FIELD", value.numeric(1))
                        .where(Condition.eq(value.field("ID"), value.numeric(1)))
                .getSql()
        );
    }
}
