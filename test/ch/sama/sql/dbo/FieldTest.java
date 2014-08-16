package ch.sama.sql.dbo;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.sama.sql.dialect.tsql.TSqlQuery;
import ch.sama.sql.dialect.tsql.TSqlValue;

public class FieldTest {
	@Test
	public void nameOnly() {
		assertEquals("NAME", new Field("NAME").toString());
	}
	
	@Test
	public void withTable() {
		assertEquals("TABLE.NAME", new Field("TABLE", "NAME").toString());
	}
	
	@Test
	public void nameAlias() {
		assertEquals("NAME AS ALIAS", new Field("NAME").as("ALIAS").toString());
	}
	
	@Test
	public void subQuery() {
		assertEquals("(\nSELECT F\n) AS ALIAS", new Field(new TSqlQuery().select(new Field("F"))).as("ALIAS").toString());
	}
	
	@Test
	public void value() {
		assertEquals("1", new Field(new TSqlValue(1)).toString());
	}
	
	@Test
	public void valueAs() {
		assertEquals("1 AS ALIAS", new Field(new TSqlValue(1)).as("ALIAS").toString());
	}
	
	@Test
	public void selectCount() {
		assertEquals(
			"SELECT COUNT(*) AS _COUNT\nFROM TABLE",
			new TSqlQuery()
				.select(
					new Field(new Function("COUNT(*)")).as("_COUNT")
				)
				.from(new Table("TABLE"))
		);
	}
}
