package ch.sama.sql.dbo;

import ch.sama.sql.tsql.dialect.TSqlTable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TableTest {
	@Test
	public void nameOnly() {
		assertEquals("[NAME]", new TSqlTable("NAME").getString());
	}
	
	@Test
	public void withSchema() {
		assertEquals("[dbo].[NAME]", new TSqlTable("dbo", "NAME").getString());
	}
}
