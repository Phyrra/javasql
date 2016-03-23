package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dialect.tsql.TSqlQueryFactory;
import ch.sama.sql.dialect.tsql.TSqlSourceFactory;
import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Order;
import ch.sama.sql.query.standard.ValueFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OffsetQueryTest {
    private static final TSqlQueryFactory sql = new TSqlQueryFactory();
    private static final TSqlValueFactory value = sql.value();
    private static final TSqlSourceFactory source = sql.source();

    @Test
    public void selectFromOrderOffset() {
        assertEquals(
                "SELECT *\nFROM [TABLE]\nORDER BY [FIELD]\nOFFSET 10 ROWS",
                sql.query()
                        .select(ValueFactory.ALL)
                        .from(source.table("TABLE"))
                        .order(Order.def(value.field("FIELD")))
                        .offset(10)
                .getSql()
        );
    }

    @Test
    public void selectFromOrderLimit() {
        assertEquals(
                "SELECT *\nFROM [TABLE]\nORDER BY [FIELD]\nOFFSET 0 ROWS\nFETCH NEXT 5 ROWS ONLY",
                sql.query()
                        .select(ValueFactory.ALL)
                        .from(source.table("TABLE"))
                        .order(Order.def(value.field("FIELD")))
                        .limit(5)
                .getSql()
        );
    }

    @Test
    public void selectFromOrderOffsetLimit() {
        assertEquals(
                "SELECT *\nFROM [TABLE]\nORDER BY [FIELD]\nOFFSET 10 ROWS\nFETCH NEXT 5 ROWS ONLY",
                sql.query()
                        .select(ValueFactory.ALL)
                        .from(source.table("TABLE"))
                        .order(Order.def(value.field("FIELD")))
                        .limit(10, 5)
                .getSql()
        );
    }

    @Test
    public void selectFromWhereOrderOffsetLimit() {
        assertEquals(
                "SELECT *\nFROM [TABLE]\nWHERE [REF] = 1\nORDER BY [FIELD]\nOFFSET 10 ROWS\nFETCH NEXT 5 ROWS ONLY",
                sql.query()
                        .select(ValueFactory.ALL)
                        .from(source.table("TABLE"))
                        .where(Condition.eq(value.field("REF"), value.numeric(1)))
                        .order(Order.def(value.field("FIELD")))
                        .limit(10, 5)
                .getSql()
        );
    }

    @Test
    public void selectFromJoinOrderOffsetLimit() {
        assertEquals(
                "SELECT *\nFROM [TABLE]\nJOIN [OTHER] ON [OTHER].[ID] = [TABLE].[REF]\nORDER BY [FIELD]\nOFFSET 10 ROWS\nFETCH NEXT 5 ROWS ONLY",
                sql.query()
                        .select(ValueFactory.ALL)
                        .from(source.table("TABLE"))
                        .join(source.table("OTHER")).on(Condition.eq(value.field("OTHER", "ID"), value.field("TABLE", "REF")))
                        .order(Order.def(value.field("FIELD")))
                        .limit(10, 5)
                .getSql()
        );
    }
}
