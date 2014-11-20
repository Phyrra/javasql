package ch.sama.sql.query.helper;

import static org.junit.Assert.*;

import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.tsql.dialect.TSqlValueFactory;
import org.junit.Test;

import ch.sama.sql.tsql.dialect.TSqlOrderParser;

public class OrderTest {
    private static final IValueFactory value = new TSqlValueFactory();
	private static final OrderParser parser = new TSqlOrderParser();
	
	@Test
	public void ascSingle() {
		Order o = Order.asc(value.field("A"));
		assertEquals("[A] ASC", o.getString(parser));
	}
	
	@Test
	public void ascMultiple() {
		Order o = Order.asc(value.field("A"), value.field("B"));
		assertEquals("[A], [B] ASC", o.getString(parser));
	}
	
	@Test
	public void descSingle() {
		Order o = Order.desc(value.field("A"));
		assertEquals("[A] DESC", o.getString(parser));
	}
	
	@Test
	public void descMultiple() {
		Order o = Order.desc(value.field("A"), value.field("B"));
		assertEquals("[A], [B] DESC", o.getString(parser));
	}

    @Test(expected = BadSqlException.class)
    public void orderAlias() {
        Order o = Order.desc(value.numeric(1).as("TEST"));
    }
}
