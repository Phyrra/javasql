package ch.sama.sql.query.helper;

import static org.junit.Assert.*;

import ch.sama.sql.query.exception.BadSqlException;
import org.junit.Test;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.tsql.dialect.TSqlOrderParser;
import ch.sama.sql.tsql.dialect.TSqlValue;

public class OrderTest {
	private static final OrderParser parser = new TSqlOrderParser();
	
	@Test
	public void ascSingle() {
		Order o = Order.asc(new TSqlValue(new Field("A")));
		assertEquals("[A] ASC", o.toString(parser));
	}
	
	@Test
	public void ascMultiple() {
		Order o = Order.asc(new TSqlValue(new Field("A")), new TSqlValue(new Field("B")));
		assertEquals("[A], [B] ASC", o.toString(parser));
	}
	
	@Test
	public void descSingle() {
		Order o = Order.desc(new TSqlValue(new Field("A")));
		assertEquals("[A] DESC", o.toString(parser));
	}
	
	@Test
	public void descMultiple() {
		Order o = Order.desc(new TSqlValue(new Field("A")), new TSqlValue(new Field("B")));
		assertEquals("[A], [B] DESC", o.toString(parser));
	}

    @Test(expected = BadSqlException.class)
    public void orderAlias() {
        Order o = Order.desc(new TSqlValue(1).as("TEST"));
    }
}
