package ch.sama.sql.dbo;

import ch.sama.sql.tsql.dialect.TSqlTable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TableTest {
	@Test
	public void nameOnly() {
		assertEquals("[NAME]", new TSqlTable("NAME").toString());
	}
	
	@Test
	public void withSchema() {
		assertEquals("[dbo].[NAME]", new TSqlTable("dbo", "NAME").toString());
	}

    @Test
    public void withAlias() {
        assertEquals("[NAME] AS [OTHER]", new TSqlTable("NAME").as("OTHER").toString());
    }
}
