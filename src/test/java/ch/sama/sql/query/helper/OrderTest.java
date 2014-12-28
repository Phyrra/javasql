package ch.sama.sql.query.helper;

import ch.sama.sql.query.base.IQueryFactory;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.helper.order.IOrder;
import ch.sama.sql.query.helper.order.IOrderRenderer;
import ch.sama.sql.tsql.dialect.TSqlQueryFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderTest {
    private static final IQueryFactory fac = new TSqlQueryFactory();
    private static final IValueFactory value = fac.value();
    private static final IOrderRenderer parser = fac.order();
	
	@Test
	public void ascSingle() {
		IOrder o = Order.asc(value.field("A"));
		assertEquals("[A] ASC", o.render(parser));
	}
	
	@Test
	public void ascMultiple() {
		IOrder o = Order.asc(value.field("A"), value.field("B"));
		assertEquals("[A], [B] ASC", o.render(parser));
	}
	
	@Test
	public void descSingle() {
		IOrder o = Order.desc(value.field("A"));
		assertEquals("[A] DESC", o.render(parser));
	}
	
	@Test
	public void descMultiple() {
		IOrder o = Order.desc(value.field("A"), value.field("B"));
		assertEquals("[A], [B] DESC", o.render(parser));
	}
}
