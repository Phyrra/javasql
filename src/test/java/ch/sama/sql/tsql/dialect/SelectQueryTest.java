package ch.sama.sql.tsql.dialect;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.sama.sql.dbo.Field;

public class SelectQueryTest {
	private static final TSqlQuery query = new TSqlQuery();
	
	@Test
	public void single() {
		assertEquals("SELECT [A]", query.select(new TSqlValue(new Field("A"))).toString());
	}
	
	@Test
	public void multi() {
		assertEquals("SELECT [A], [B]", query.select(new TSqlValue(new Field("A")), new TSqlValue(new Field("B"))).toString());
	}
	
	@Test
	public void top() {
		assertEquals("SELECT TOP 10 [A]", query.select(new TSqlValue(new Field("A"))).top(10).toString());
	}
}
