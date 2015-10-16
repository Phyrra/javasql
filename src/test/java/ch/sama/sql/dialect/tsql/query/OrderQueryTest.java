package ch.sama.sql.dialect.tsql.query;

import static org.junit.Assert.*;

import ch.sama.sql.query.base.FromQuery;
import ch.sama.sql.query.helper.order.IOrder;
import ch.sama.sql.dialect.tsql.TSqlQueryFactory;
import ch.sama.sql.dialect.tsql.TSqlSourceFactory;
import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import org.junit.Test;

import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Order;

public class OrderQueryTest {
    private static final TSqlQueryFactory sql = new TSqlQueryFactory();
    private static final TSqlValueFactory value = sql.value();
    private static final TSqlSourceFactory source = sql.source();

	private static final FromQuery query = sql.query().select(value.field("F")).from(source.table("T"));
	private static final IOrder order = Order.asc(value.field("F"));
	
	@Test
	public void afterFrom() {
		assertEquals(
                "SELECT [F]\nFROM [T]\nORDER BY [F] ASC",
                query.order(order).getSql()
        );
	}
	
	@Test
	public void afterJoin() {
		assertEquals(
                "SELECT [F]\nFROM [T]\nJOIN [J] ON 2 = 2\nORDER BY [F] ASC",
                query
                        .join(source.table("J"))
                                .on(Condition.eq(value.numeric(2), value.numeric(2)))
                        .order(order)
                .getSql()
		);
	}

    @Test
    public void multiple() {
        assertEquals(
                "SELECT [F]\nFROM [T]\nORDER BY [A] ASC, [B] DESC",
                query
                        .order(
                                Order.asc(value.field("A")),
                                Order.desc(value.field("B"))
                        )
                .getSql()
        );
    }
}
