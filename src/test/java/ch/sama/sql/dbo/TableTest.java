package ch.sama.sql.dbo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TableTest {
	@Test
	public void nameOnly() {
		assertEquals("[NAME]", new Table("NAME").toString());
	}
	
	@Test
	public void withSchema() {
		assertEquals("[dbo].[NAME]", new Table("dbo", "NAME").toString());
	}

    @Test
    public void withAlias() {
        assertEquals("[NAME] AS [OTHER]", new Table("NAME").as("OTHER").toString());
    }
}
