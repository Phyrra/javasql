package ch.sama.sql.dbo;

import static org.junit.Assert.*;

import org.junit.Test;

public class FieldTest {
	@Test
	public void nameOnly() {
		assertEquals("[NAME]", new Field("NAME").toString());
	}
	
	@Test
	public void withTableName() {
		assertEquals("[TABLE].[NAME]", new Field("TABLE", "NAME").toString());
	}

    @Test
    public void withTable() {
        assertEquals("[TABLE].[NAME]", new Field(new Table("TABLE"), "NAME").toString());
    }

    @Test
    public void withAliasTable() {
        // e.g. from schema
        Table t = new Table("TABLE");

        // after using it in a join or equal
        t.as("OTHER");

        assertEquals("[TABLE].[NAME]", new Field(t, "NAME").toString());
    }
}
