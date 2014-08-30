package ch.sama.sql.tsql.dialect;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.SelectQuery;

public class FromQueryTest {
	private static final SelectQuery query = new TSqlQuery().select(new TSqlValue(new Field("F")));
	
	@Test
	public void single() {
		assertEquals("SELECT [F]\nFROM [A]", query.from(new Table("A")).toString());
	}
	
	@Test
	public void multi() {
		assertEquals("SELECT [F]\nFROM [A], [B]", query.from(new Table("A"), new Table("B")).toString());
	}
}
