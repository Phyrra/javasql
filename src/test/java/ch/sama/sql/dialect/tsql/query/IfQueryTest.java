package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dialect.tsql.TSqlQueryFactory;
import ch.sama.sql.dialect.tsql.TSqlSourceFactory;
import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import ch.sama.sql.query.helper.Condition;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IfQueryTest {
    private static final TSqlQueryFactory fac = new TSqlQueryFactory();
    private static final TSqlValueFactory value = fac.value();
    private static final TSqlSourceFactory source = fac.source();

    @Test
    public void ifNoElse() {
        assertEquals(
                "IF (\n" +
                "1 = 1\n" +
                ")\n" +
                "SELECT 1",
                fac.query()
                        .doif(
                                Condition.eq(value.numeric(1), value.numeric(1)),
                                fac.query()
                                        .select(value.numeric(1))
                        )
                .getSql()
        );
    }

    @Test
    public void ifWithElse() {
        assertEquals(
                "IF (\n" +
                "1 = 2\n" +
                ")\n" +
                "SELECT 1\n" +
                "ELSE\n" +
                "SELECT 2",
                fac.query()
                        .doif(
                                Condition.eq(value.numeric(1), value.numeric(2)),
                                fac.query()
                                        .select(value.numeric(1))
                        )
                        .otherwise(
                                fac.query()
                                        .select(value.numeric(2))
                        )
                .getSql()
        );
    }
}
