package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.dialect.tsql.TSqlQueryFactory;
import ch.sama.sql.dialect.tsql.TSqlSourceFactory;
import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeleteQueryTest {
    private static final TSqlQueryFactory sql = new TSqlQueryFactory();
    private static final TSqlValueFactory value = sql.value();
    private static final TSqlSourceFactory source = sql.source();

    @Test
    public void deleteFromString() {
        assertEquals(
                "DELETE FROM [TABLE]",
                sql.query()
                        .delete().from("TABLE")
                .getSql()
        );
    }

    @Test
    public void deleteFromTable() {
        assertEquals(
                "DELETE FROM [dbo].[TABLE]",
                sql.query()
                        .delete().from(new Table("dbo", "TABLE"))
                .getSql()
        );
    }
    
    @Test
    public void deleteCondition() {
        assertEquals(
                "DELETE FROM [TABLE]\nWHERE [ID] = 1",
                sql.query()
                        .delete().from("TABLE").where(Condition.eq(value.field("ID"), value.numeric(1)))
                .getSql()
        );
    }
    
    @Test
    public void deleteCte() {
        assertEquals(
                "WITH [CTE] AS (\nSELECT [FIELD1]\nFROM [TABLE1]\n)\nDELETE FROM [TABLE2]\nWHERE [FIELD2] IN (\nSELECT [FIELD1]\nFROM [CTE]\n)",
                sql.query()
                        .with("CTE")
                        .as(
                                sql.query()
                                        .select(value.field("FIELD1"))
                                        .from(source.table("TABLE1"))
                        )
                        .delete().from(new Table("TABLE2"))
                        .where(Condition.in(
                                value.field("FIELD2"),
                                sql.query()
                                        .select(value.field("FIELD1"))
                                        .from(source.table("CTE"))
                        ))
                .getSql()
        );
    }
}
