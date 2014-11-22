package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.base.IQueryFactory;
import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.exception.BadParameterException;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunctionTest {
    private static final IQueryFactory fac = new TSqlQueryFactory();
    private static final IValueFactory value = fac.value();
    private static final IQueryRenderer renderer = fac.renderer();
	private static final TSqlFunctionFactory function = new TSqlFunctionFactory();

    @Test
    public void noParam() {
        assertEquals("fnc()", value.function("fnc()").getString(renderer));
    }

    @Test
    public void oneParam() {
        assertEquals("fnc(param)", value.function("fnc(param)").getString(renderer));
    }

    @Test
    public void multiParam() {
        assertEquals("fnc(param1, param2)", value.function("fnc(param1, param2)").getString(renderer));
    }

    @Test
    public void nestedParam() {
        assertEquals("fnc(param, inner(param))", value.function("fnc(param, inner(param))").getString(renderer));
    }

    @Test (expected = BadParameterException.class)
    public void badFunction() {
        value.function("fnc(*");
    }

    @Test (expected = BadParameterException.class)
    public void noFunction() {
        value.function("(*)");
    }

    @Test (expected = BadParameterException.class)
    public void noFunctionNested() {
        value.function("(inner())");
    }

    @Test (expected = IllegalIdentifierException.class)
    public void badFunctionName() {
        value.function("fun'ction(param)");
    }

    @Test
    public void coalesce() {
        assertEquals("COALESCE(([FIELD]), (1))", function.coalesce(value.field("FIELD"), value.numeric(1)).getString());
    }

    @Test
    public void caseWhen() {
        assertEquals(
            "(CASE [FIELD]\nWHEN 1 THEN 'ONE'\nWHEN 2 THEN 'TWO'\nELSE 'UNKNOWN'\nEND)",
            function.caseWhen(
                    value.field("FIELD"),
                    function.whenThen(value.numeric(1), value.string("ONE")),
                    function.whenThen(value.numeric(2), value.string("TWO")),
                    function.whenThen(null, value.string("UNKNOWN"))
            ).getString());
    }

    @Test(expected = BadParameterException.class)
    public void caseElse() {
        function.caseWhen(
                value.field("FIELD"),
                function.whenThen(value.numeric(1), value.string("ONE")),
                function.whenThen(null, value.string("UNKNOWN")),
                function.whenThen(value.numeric(2), value.string("TWO"))
        );
    }
}
