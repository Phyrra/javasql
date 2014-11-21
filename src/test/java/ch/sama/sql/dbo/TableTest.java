package ch.sama.sql.dbo;

import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.tsql.dialect.TSqlQueryRenderer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TableTest {
    private static final IQueryRenderer renderer = new TSqlQueryRenderer();

	@Test
	public void nameOnly() {
		assertEquals("[NAME]", new Table("NAME").getString(renderer));
	}
	
	@Test
	public void withSchema() {
		assertEquals("[dbo].[NAME]", new Table("dbo", "NAME").getString(renderer));
	}
}
