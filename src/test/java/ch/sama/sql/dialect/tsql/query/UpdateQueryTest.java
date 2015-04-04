package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.dialect.tsql.TSqlQueryBuilder;
import ch.sama.sql.dialect.tsql.TSqlSourceFactory;
import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UpdateQueryTest {
    private static final TSqlQueryBuilder sql = new TSqlQueryBuilder();
    private static final TSqlValueFactory value = sql.value();
    private static final TSqlSourceFactory source = sql.source();
    
    @Test
    public void updateString() {
        assertEquals(
                "UPDATE [TABLE]",
                sql.query()
                        .update("TABLE")
                .getSql()
        );
    }
    
    @Test
    public void updateTable() {
        assertEquals(
                "UPDATE [dbo].[TABLE]",
                sql.query()
                        .update(new Table("dbo", "TABLE"))
                .getSql()
        );
    }
    
    @Test
    public void setOneStringField() {
        assertEquals(
                "UPDATE [TABLE] SET\n[FIELD] = 1",
                sql.query()
                        .update("TABLE")
                        .set("FIELD", value.numeric(1))
                .getSql()
        );
    }
    
    @Test
    public void setOneField() {
        assertEquals(
                "UPDATE [TABLE] SET\n[FIELD] = 1",
                sql.query()
                        .update("TABLE")
                        .set(new Field("TABLE", "FIELD"), value.numeric(1))
                .getSql()
        );
    }

    @Test
    public void setMultiStringField() {
        assertEquals(
                "UPDATE [TABLE] SET\n[FIELD1] = 1,\n[FIELD2] = 2",
                sql.query()
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
                sql.query()
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
                sql.query()
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
                sql.query()
                        .with("CTE")
                        .as(
                                sql.query()
                                        .select(value.field("FIELD1")).top(1)
                                        .from(source.table("TABLE1"))
                        )
                        .update("TABLE2")
                        .set(
                                "FIELD2",
                                value.query(
                                        sql.query()
                                                .select(value.value(Value.VALUE.ALL))
                                                .from(source.table("CTE"))
                                )
                        )
                .getSql()
        );
    }
}
