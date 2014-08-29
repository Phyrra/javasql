package ch.sama.sql.tsql.dialect;

import static org.junit.Assert.*;

import ch.sama.sql.tsql.dialect.TSqlQuery;
import ch.sama.sql.tsql.dialect.TSqlValue;
import org.junit.Test;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.FromQuery;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Order;

public class OrderQueryTest {
	private static final FromQuery query = new TSqlQuery().select(new TSqlValue(new Field("F"))).from(new Table("T"));
	private static final Order order = Order.asc(new TSqlValue(new Field("F")));
	
	@Test
	public void afterFrom() {
		assertEquals("SELECT F\nFROM T\nORDER BY F ASC", query.order(order).toString());
	}
	
	@Test
	public void afterJoin() {
		assertEquals(
			"SELECT F\nFROM T\nJOIN J ON 2 = 2\nORDER BY F ASC",
			query
				.join(new Table("J"))
					.on(Condition.eq(new TSqlValue(2), new TSqlValue(2)))
				.order(order).toString()
		);
	}
}
