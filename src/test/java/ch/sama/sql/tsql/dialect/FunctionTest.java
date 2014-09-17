package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.query.exception.BadParameterException;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunctionTest {
    private static final QueryFactory qfac = new TSqlQueryFactory();
	private static final TSqlFunctionFactory fac = new TSqlFunctionFactory();

    @Test
    public void noParam() {
        assertEquals("fnc()", qfac.function("fnc()").toString());
    }

    @Test
    public void oneParam() {
        assertEquals("fnc(param)", qfac.function("fnc(param)").toString());
    }

    @Test
    public void multiParam() {
        assertEquals("fnc(param1, param2)", qfac.function("fnc(param1, param2)").toString());
    }

    @Test
    public void nestedParam() {
        assertEquals("fnc(param, inner(param))", qfac.function("fnc(param, inner(param))").toString());
    }

    @Test (expected = BadParameterException.class)
    public void badFunction() {
        qfac.function("fnc(*");
    }

    @Test (expected = BadParameterException.class)
    public void noFunction() {
        qfac.function("(*)");
    }

    @Test (expected = BadParameterException.class)
    public void noFunctionNested() {
        qfac.function("(inner())");
    }

    @Test (expected = IllegalIdentifierException.class)
    public void badFunctionName() {
        qfac.function("fun'ction(param)");
    }

    @Test
    public void coalesce() {
        assertEquals("COALESCE([FIELD], 1)", fac.coalesce(qfac.field("FIELD"), qfac.numeric(1)).toString());
    }

    @Test
    public void caseWhen() {
        assertEquals(
            "(CASE [FIELD]\nWHEN 1 THEN 'ONE'\nWHEN 2 THEN 'TWO'\nELSE 'UNKNOWN'\nEND)",
            fac.caseWhen(
                qfac.field("FIELD"),
                fac.whenThen(qfac.numeric(1), qfac.string("ONE")),
                fac.whenThen(qfac.numeric(2), qfac.string("TWO")),
                fac.whenThen(null, qfac.string("UNKNOWN"))
            ).toString());
    }

    @Test(expected = BadParameterException.class)
    public void caseElse() {
        fac.caseWhen(
            qfac.field("FIELD"),
            fac.whenThen(qfac.numeric(1), qfac.string("ONE")),
            fac.whenThen(null, qfac.string("UNKNOWN")),
            fac.whenThen(qfac.numeric(2), qfac.string("TWO"))
        );
    }
}
