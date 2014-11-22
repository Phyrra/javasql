package ch.sama.sql.dbo;

import ch.sama.sql.query.base.IQueryFactory;
import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.tsql.dialect.TSqlQueryFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TableTest {
    private static final IQueryFactory fac = new TSqlQueryFactory();
    private static final IQueryRenderer renderer = fac.renderer();

    @Test
	public void nameOnly() {
		assertEquals("[NAME]", new Table("NAME").getString(renderer));
	}
	
	@Test
	public void withSchema() {
		assertEquals("[dbo].[NAME]", new Table("dbo", "NAME").getString(renderer));
	}
}
