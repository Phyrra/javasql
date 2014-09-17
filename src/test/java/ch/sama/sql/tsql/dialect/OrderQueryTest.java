package ch.sama.sql.tsql.dialect;

import static org.junit.Assert.*;

import ch.sama.sql.query.base.QueryFactory;
import org.junit.Test;

import ch.sama.sql.query.base.FromQuery;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Order;

public class OrderQueryTest {
    private static final QueryFactory fac = new TSqlQueryFactory();
	private static final FromQuery query = fac.create().select(fac.field("F")).from(fac.table("T"));
	private static final Order order = Order.asc(fac.field("F"));
	
	@Test
	public void afterFrom() {
		assertEquals("SELECT [F]\nFROM [T]\nORDER BY [F] ASC", query.order(order).toString());
	}
	
	@Test
	public void afterJoin() {
		assertEquals(
			"SELECT [F]\nFROM [T]\nJOIN [J] ON 2 = 2\nORDER BY [F] ASC",
			query
				.join(fac.table("J"))
					.on(Condition.eq(fac.numeric(2), fac.numeric(2)))
				.order(order)
            .toString()
		);
	}

    @Test
    public void multiple() {
        assertEquals(
            "SELECT [F]\nFROM [T]\nORDER BY [A] ASC, [B] DESC",
            query
                .order(Order.asc(fac.field("A")))
                .order(Order.desc(fac.field("B")))
            .toString()
        );
    }
}
