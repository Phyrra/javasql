package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.query.exception.BadParameterException;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Order;
import ch.sama.sql.dialect.tsql.TSqlFunctionFactory;
import ch.sama.sql.dialect.tsql.TSqlQueryFactory;
import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunctionTest {
    private static final TSqlQueryFactory fac = new TSqlQueryFactory();
    private static final TSqlValueFactory value = fac.value();
    private static final TSqlQueryRenderer renderer = fac.renderer();
	private static final TSqlFunctionFactory function = new TSqlFunctionFactory();

    @Test
    public void noParam() {
        assertEquals("fnc()", renderer.render(value.function("fnc")));
    }

    @Test
    public void oneParam() {
        assertEquals("fnc(param)", renderer.render(value.function("fnc", value.plain("param"))));
    }

    @Test
    public void multiParam() {
        assertEquals("fnc(param1, param2)", renderer.render(value.function("fnc", value.plain("param1"), value.plain("param2"))));
    }

    @Test
    public void nestedFunction() {
        assertEquals("fnc(param, inner(param))", renderer.render(value.function("fnc", value.plain("param"), value.function("inner", value.plain("param")))));
    }

    @Test (expected = IllegalIdentifierException.class)
    public void badFunctionName() {
        value.function("fun'ction");
    }

    @Test
    public void coalesce() {
        assertEquals("COALESCE([FIELD], 1)", renderer.render(function.coalesce(value.field("FIELD"), value.numeric(1))));
    }

    @Test
    public void valueCaseWhen() {
        assertEquals(
                "(CASE [FIELD]\nWHEN 1 THEN 'ONE'\nWHEN 2 THEN 'TWO'\nELSE 'UNKNOWN'\nEND)",
                renderer.render(
                        function.caseWhen(
                                value.field("FIELD"),
                                function.whenThen(value.numeric(1), value.string("ONE")),
                                function.whenThen(value.numeric(2), value.string("TWO")),
                                function.otherwise(value.string("UNKNOWN"))
                        )
                )
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
                renderer.render(
                        function.caseWhen(
                                function.whenThen(Condition.eq(value.field("FIELD"), value.numeric(1)), value.string("ONE")),
                                function.whenThen(Condition.eq(value.field("FIELD"), value.numeric(2)), value.string("TWO")),
                                function.otherwise(value.string("UNKNOWN"))
                        )
                )
        );
    }
    
    @Test
    public void rowNumberOneValueOneOrder() {
        assertEquals(
                "ROW_NUMBER() OVER (PARTITION BY [FIELD1] ORDER BY [FIELD2])",
                renderer.render(
                        function.rowNumber(
                                function.valueList(value.field("FIELD1")),
                                function.orderList(Order.def(value.field("FIELD2")))
                        )
                )
        );
    }

    @Test
    public void rowNumberTwoValuesTwoOrders() {
        assertEquals(
                "ROW_NUMBER() OVER (PARTITION BY [FIELD1], [FIELD2] ORDER BY [FIELD3] ASC, [FIELD4] DESC)",
                renderer.render(
                        function.rowNumber(
                                function.valueList(value.field("FIELD1"), value.field("FIELD2")),
                                function.orderList(Order.asc(value.field("FIELD3")), Order.desc(value.field("FIELD4")))
                        )
                )
        );
    }

    @Test
    public void minValue() {
        assertEquals(
                "(CASE\nWHEN [FIELD1] < [FIELD2] THEN [FIELD1]\nELSE [FIELD2]\nEND)",
                renderer.render(
                        function.min(value.field("FIELD1"), value.field("FIELD2"))
                )
        );
    }

    @Test
    public void maxValue() {
        assertEquals(
                "(CASE\nWHEN [FIELD1] > [FIELD2] THEN [FIELD1]\nELSE [FIELD2]\nEND)",
                renderer.render(
                        function.max(value.field("FIELD1"), value.field("FIELD2"))
                )
        );
    }
}