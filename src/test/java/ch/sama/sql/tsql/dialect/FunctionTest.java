package ch.sama.sql.tsql.dialect;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Function;
import ch.sama.sql.query.exception.BadParameterException;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunctionTest {
	private static final TSqlFunctionFactory fac = new TSqlFunctionFactory();

    @Test
    public void noParam() {
        assertEquals("fnc()", new TSqlValue(new Function("fnc()")).toString());
    }

    @Test
    public void oneParam() {
        assertEquals("fnc(param)", new TSqlValue(new Function("fnc(param)")).toString());
    }

    @Test
    public void multiParam() {
        assertEquals("fnc(param1, param2)", new TSqlValue(new Function("fnc(param1, param2)")).toString());
    }

    @Test
    public void nestedParam() {
        assertEquals("fnc(param, inner(param))", new TSqlValue(new Function("fnc(param, inner(param))")).toString());
    }

    @Test (expected = BadParameterException.class)
    public void badFunction() {
        new Function("fnc(*");
    }

    @Test (expected = BadParameterException.class)
    public void noFunction() {
        new Function("(*)");
    }

    @Test (expected = BadParameterException.class)
    public void noFunctionNested() {
        new Function("(inner())");
    }

    @Test (expected = IllegalIdentifierException.class)
    public void badFunctionName() {
        new Function("fun'ction(param)");
    }

    @Test
    public void coalesce() {
        assertEquals("COALESCE([FIELD], 1)", fac.coalesce(new TSqlValue(new Field("FIELD")), new TSqlValue(1)).toString());
    }

    @Test
    public void caseWhen() {
        assertEquals(
            "(CASE [FIELD]\nWHEN 1 THEN 'ONE'\nWHEN 2 THEN 'TWO'\nELSE 'UNKNOWN'\nEND)",
            fac.caseWhen(
                new TSqlValue(new Field("FIELD")),
                fac.whenThen(new TSqlValue(1), new TSqlValue("ONE")),
                fac.whenThen(new TSqlValue(2), new TSqlValue("TWO")),
                fac.whenThen(null, new TSqlValue("UNKNOWN"))
            ).toString());
    }

    @Test(expected = BadParameterException.class)
    public void caseElse() {
        fac.caseWhen(
            new TSqlValue(new Field("FIELD")),
            fac.whenThen(new TSqlValue(1), new TSqlValue("ONE")),
            fac.whenThen(null, new TSqlValue("UNKNOWN")),
            fac.whenThen(new TSqlValue(2), new TSqlValue("TWO"))
        );
    }
}
