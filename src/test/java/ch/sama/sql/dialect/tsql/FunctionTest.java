package ch.sama.sql.dialect.tsql;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.query.exception.BadParametersException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunctionTest {
	private static final TSqlFunctionFactory fac = new TSqlFunctionFactory();

    @Test
    public void coalesce() {
        assertEquals("COALESCE(FIELD, 1)", fac.coalesce(new TSqlValue(new Field("FIELD")), new TSqlValue(1)).toString());
    }

    @Test
    public void caseWhen() {
        assertEquals(
            "(CASE FIELD\nWHEN 1 THEN 'ONE'\nWHEN 2 THEN 'TWO'\nELSE 'UNKNOWN'\nEND)",
            fac.caseWhen(
                new TSqlValue(new Field("FIELD")),
                fac.whenThen(new TSqlValue(1), new TSqlValue("ONE")),
                fac.whenThen(new TSqlValue(2), new TSqlValue("TWO")),
                fac.whenThen(null, new TSqlValue("UNKNOWN"))
            ).toString());
    }

    @Test(expected = BadParametersException.class)
    public void caseElse() {
        fac.caseWhen(
            new TSqlValue(new Field("FIELD")),
            fac.whenThen(new TSqlValue(1), new TSqlValue("ONE")),
            fac.whenThen(null, new TSqlValue("UNKNOWN")),
            fac.whenThen(new TSqlValue(2), new TSqlValue("TWO"))
        );
    }
}
