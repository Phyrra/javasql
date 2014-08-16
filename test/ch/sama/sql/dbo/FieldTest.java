package ch.sama.sql.dbo;

import static org.junit.Assert.*;

import org.junit.Test;

public class FieldTest {
	@Test
	public void nameOnly() {
		assertEquals("NAME", new Field("NAME").toString());
	}
	
	@Test
	public void withTable() {
		assertEquals("TABLE.NAME", new Field("TABLE", "NAME").toString());
	}
}
