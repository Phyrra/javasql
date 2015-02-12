package ch.sama.sql.tsql.dialect;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.IQueryFactory;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.helper.Condition;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeleteQueryTest {
    private static final IQueryFactory fac = new TSqlQueryFactory();
    private static final IValueFactory value = fac.value();

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
}
