package ch.sama.sql.dbo;

import static org.junit.Assert.*;

import ch.sama.sql.tsql.dialect.TSqlField;
import ch.sama.sql.tsql.dialect.TSqlTable;
import org.junit.Test;

public class FieldTest {
	@Test
	public void nameOnly() {
		assertEquals("[NAME]", new TSqlField("NAME").getString());
	}
	
	@Test
	public void withTableName() {
		assertEquals("[TABLE].[NAME]", new TSqlField("TABLE", "NAME").getString());
	}

    @Test
    public void withTable() {
        assertEquals("[TABLE].[NAME]", new TSqlField(new TSqlTable("TABLE"), "NAME").getString());
    }
}
