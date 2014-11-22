package ch.sama.sql.query.helper;

import static org.junit.Assert.*;

import ch.sama.sql.query.base.IQueryFactory;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.tsql.dialect.TSqlQueryFactory;
import org.junit.Test;

public class OrderTest {
    private static final IQueryFactory fac = new TSqlQueryFactory();
    private static final IValueFactory value = fac.value();
    private static final IOrderParser parser = fac.order();
	
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
