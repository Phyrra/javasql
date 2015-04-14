package ch.sama.sql.query.helper;

import ch.sama.sql.query.helper.order.IOrder;
import ch.sama.sql.dialect.tsql.TSqlOrderRenderer;
import ch.sama.sql.dialect.tsql.TSqlQueryFactory;
import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderTest {
    private static final TSqlQueryFactory sql = new TSqlQueryFactory();
    private static final TSqlValueFactory value = sql.value();
    private static final TSqlOrderRenderer order = sql.order();
	
	@Test
	public void ascSingle() {
		IOrder o = Order.asc(value.field("A"));
		assertEquals("[A] ASC", o.render(order));
	}
	
	@Test
	public void ascMultiple() {
		IOrder o = Order.asc(value.field("A"), value.field("B"));
		assertEquals("[A], [B] ASC", o.render(order));
	}
	
	@Test
	public void descSingle() {
		IOrder o = Order.desc(value.field("A"));
		assertEquals("[A] DESC", o.render(order));
	}
	
	@Test
	public void descMultiple() {
		IOrder o = Order.desc(value.field("A"), value.field("B"));
		assertEquals("[A], [B] DESC", o.render(order));
	}
}
