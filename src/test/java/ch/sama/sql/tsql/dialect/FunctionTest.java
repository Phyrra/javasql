package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.base.IQueryFactory;
import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.exception.BadParameterException;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Order;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunctionTest {
    private static final IQueryFactory fac = new TSqlQueryFactory();
    private static final IValueFactory value = fac.value();
    private static final IQueryRenderer renderer = fac.renderer();
	private static final TSqlFunctionFactory function = new TSqlFunctionFactory();

    @Test
    public void noParam() {
        assertEquals("fnc()", value.function("fnc").getString(renderer));
    }

    @Test
    public void oneParam() {
        assertEquals("fnc(param)", value.function("fnc", value.plain("param")).getString(renderer));
    }

    @Test
    public void multiParam() {
        assertEquals("fnc(param1, param2)", value.function("fnc", value.plain("param1"), value.plain("param2")).getString(renderer));
    }

    @Test
    public void nestedFunction() {
        assertEquals("fnc(param, inner(param))", value.function("fnc", value.plain("param"), value.function("inner", value.plain("param"))).getString(renderer));
    }

    @Test (expected = IllegalIdentifierException.class)
    public void badFunctionName() {
        value.function("fun'ction");
    }

    @Test
    public void coalesce() {
        assertEquals("COALESCE([FIELD], 1)", function.coalesce(value.field("FIELD"), value.numeric(1)).getString(renderer));
    }

    @Test
    public void valueCaseWhen() {
        assertEquals(
                "(CASE [FIELD]\nWHEN 1 THEN 'ONE'\nWHEN 2 THEN 'TWO'\nELSE 'UNKNOWN'\nEND)",
                function.caseWhen(
                        value.field("FIELD"),
                        function.whenThen(value.numeric(1), value.string("ONE")),
                        function.whenThen(value.numeric(2), value.string("TWO")),
                        function.otherwise(value.string("UNKNOWN"))
                )
                .getString(renderer)
        );
    }

    @Test(expected = BadParameterException.class)
    public void caseElse() {
        function.caseWhen(
                value.field("FIELD"),
                function.whenThen(value.numeric(1), value.string("ONE")),
                function.otherwise(value.string("UNKNOWN")),
                function.whenThen(value.numeric(2), value.string("TWO"))
        );
    }

    @Test
    public void conditionCaseWhen() {
        assertEquals(
                "(CASE\nWHEN [FIELD] = 1 THEN 'ONE'\nWHEN [FIELD] = 2 THEN 'TWO'\nELSE 'UNKNOWN'\nEND)",
                function.caseWhen(
                        function.whenThen(Condition.eq(value.field("FIELD"), value.numeric(1)), value.string("ONE")),
                        function.whenThen(Condition.eq(value.field("FIELD"), value.numeric(2)), value.string("TWO")),
                        function.otherwise(value.string("UNKNOWN"))
                )
                .getString(renderer)
        );
    }
    
    @Test
    public void rowNumberOneValueOneOrder() {
        assertEquals(
                "ROW_NUMBER() OVER (PARTITION BY [FIELD1] ORDER BY [FIELD2])",
                function.rowNumber(
                        function.valueList(value.field("FIELD1")),
                        function.orderList(Order.def(value.field("FIELD2")))
                )
                .getString(renderer)
        );
    }

    @Test
    public void rowNumberTwoValuesTwoOrders() {
        assertEquals(
                "ROW_NUMBER() OVER (PARTITION BY [FIELD1], [FIELD2] ORDER BY [FIELD3] ASC, [FIELD4] DESC)",
                function.rowNumber(
                        function.valueList(value.field("FIELD1"), value.field("FIELD2")),
                        function.orderList(Order.asc(value.field("FIELD3")), Order.desc(value.field("FIELD4")))
                )
                .getString(renderer)
        );
    }
    
}
