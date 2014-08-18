package ch.sama.sql.dialect.tsql;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.FromQuery;
import ch.sama.sql.query.helper.Condition;

public class JoinQueryTest {
	private static final FromQuery query = new TSqlQuery().select(new TSqlValue(new Field("F"))).from(new Table("T"));
	
	@Test
	public void single() {
		Condition c = Condition.eq(new TSqlValue(1), new TSqlValue(1));
		assertEquals("SELECT F\nFROM T\nJOIN J ON 1 = 1", query.join(new Table("J")).on(c).toString());
	}
	
	@Test
	public void multiple() {
		Condition c1 = Condition.eq(new TSqlValue(1), new TSqlValue(1));
		Condition c2 = Condition.eq(new TSqlValue(2), new TSqlValue(2));
		
		assertEquals(
			"SELECT F\nFROM T\nJOIN J1 ON 1 = 1\nJOIN J2 ON 2 = 2",
			query
				.join(new Table("J1")).on(c1)
				.join(new Table("J2")).on(c2)
			.toString()
		);
	}
}
