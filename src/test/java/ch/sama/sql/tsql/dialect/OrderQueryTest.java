package ch.sama.sql.tsql.dialect;

import static org.junit.Assert.*;

import ch.sama.sql.query.base.*;
import org.junit.Test;

import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Order;

public class OrderQueryTest {
    private static final IQueryRenderer renderer = new TSqlQueryRenderer();
    private static final IValueFactory value = new TSqlValueFactory();
    private static final ISourceFactory source = new TSqlSourceFactory();
	private static final FromQuery query = new Query().select(value.field("F")).from(source.table("T"));
	private static final Order order = Order.asc(value.field("F"));
	
	@Test
	public void afterFrom() {
		assertEquals("SELECT [F]\nFROM [T]\nORDER BY [F] ASC", query.order(order).getSql(renderer));
	}
	
	@Test
	public void afterJoin() {
		assertEquals(
			"SELECT [F]\nFROM [T]\nJOIN [J] ON 2 = 2\nORDER BY [F] ASC",
			query
                    .join(source.table("J"))
                            .on(Condition.eq(value.numeric(2), value.numeric(2)))
                    .order(order)
            .getSql(renderer)
		);
	}

    @Test
    public void multiple() {
        assertEquals(
            "SELECT [F]\nFROM [T]\nORDER BY [A] ASC, [B] DESC",
            query
                    .order(Order.asc(value.field("A")))
                    .order(Order.desc(value.field("B")))
            .getSql(renderer)
        );
    }
}
