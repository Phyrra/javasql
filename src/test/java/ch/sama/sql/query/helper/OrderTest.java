package ch.sama.sql.query.helper;

import static org.junit.Assert.*;

import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.tsql.dialect.TSqlQueryFactory;
import org.junit.Test;

import ch.sama.sql.tsql.dialect.TSqlOrderParser;

public class OrderTest {
    private static final QueryFactory fac = new TSqlQueryFactory();
	private static final OrderParser parser = new TSqlOrderParser();
	
	@Test
	public void ascSingle() {
		Order o = Order.asc(fac.field("A"));
		assertEquals("[A] ASC", o.toString(parser));
	}
	
	@Test
	public void ascMultiple() {
		Order o = Order.asc(fac.field("A"), fac.field("B"));
		assertEquals("[A], [B] ASC", o.toString(parser));
	}
	
	@Test
	public void descSingle() {
		Order o = Order.desc(fac.field("A"));
		assertEquals("[A] DESC", o.toString(parser));
	}
	
	@Test
	public void descMultiple() {
		Order o = Order.desc(fac.field("A"), fac.field("B"));
		assertEquals("[A], [B] DESC", o.toString(parser));
	}

    @Test(expected = BadSqlException.class)
    public void orderAlias() {
        Order o = Order.desc(fac.numeric(1).as("TEST"));
    }
}
