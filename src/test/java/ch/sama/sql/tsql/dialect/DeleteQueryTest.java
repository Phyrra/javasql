package ch.sama.sql.tsql.dialect;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.IQueryFactory;
import ch.sama.sql.query.base.ISourceFactory;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.helper.Condition;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeleteQueryTest {
    private static final IQueryFactory fac = new TSqlQueryFactory();
    private static final IValueFactory value = fac.value();
    private static final ISourceFactory source = fac.source();

    @Test
    public void deleteFromString() {
        assertEquals(
                "DELETE FROM [TABLE]",
                fac.query()
                        .delete().from("TABLE")
                .getSql()
        );
    }

    @Test
    public void deleteFromTable() {
        assertEquals(
                "DELETE FROM [dbo].[TABLE]",
                fac.query()
                        .delete().from(new Table("dbo", "TABLE"))
                .getSql()
        );
    }
    
    @Test
    public void deleteCondition() {
        assertEquals(
                "DELETE FROM [TABLE]\nWHERE [ID] = 1",
                fac.query()
                        .delete().from("TABLE").where(Condition.eq(value.field("ID"), value.numeric(1)))
                .getSql()
        );
    }
    
    @Test
    public void deleteCte() {
        assertEquals(
                "WITH [CTE] AS (\nSELECT [FIELD1]\nFROM [TABLE1]\n)\nDELETE FROM [TABLE2]\nWHERE [FIELD2] IN (\nSELECT [FIELD1]\nFROM [CTE]\n)",
                fac.query()
                        .with("CTE").as(
                                fac.query()
                                        .select(value.field("FIELD1"))
                                        .from(source.table("TABLE1"))
                        )
                        .delete().from(new Table("TABLE2"))
                        .where(Condition.in(
                                value.field("FIELD2"),
                                fac.query()
                                        .select(value.field("FIELD1"))
                                        .from(source.table("CTE"))
                        ))
                .getSql()
        );
    }
}
