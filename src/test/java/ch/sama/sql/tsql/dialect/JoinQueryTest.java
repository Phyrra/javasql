package ch.sama.sql.tsql.dialect;

import static org.junit.Assert.*;

import ch.sama.sql.query.base.QueryFactory;
import org.junit.Test;

import ch.sama.sql.query.base.FromQuery;
import ch.sama.sql.query.helper.Condition;

public class JoinQueryTest {
    private static final QueryFactory fac = new TSqlQueryFactory();
	private static final FromQuery query = fac.create().select(fac.field("F")).from(fac.table("T"));
	
	@Test
	public void single() {
		Condition c = Condition.eq(new TSqlValue(1), new TSqlValue(1));
		assertEquals("SELECT [F]\nFROM [T]\nJOIN [J] ON 1 = 1", query.join(fac.table("J")).on(c).toString());
	}

    @Test
    public void alias() {
        Condition c = Condition.eq(new TSqlValue(1), new TSqlValue(1));
        assertEquals("SELECT [F]\nFROM [T]\nJOIN [J] AS [A] ON 1 = 1", query.join(fac.table("J").as("A")).on(c).toString());
    }
	
	@Test
	public void multiple() {
		Condition c1 = Condition.eq(new TSqlValue(1), new TSqlValue(1));
		Condition c2 = Condition.eq(new TSqlValue(2), new TSqlValue(2));
		
		assertEquals(
			"SELECT [F]\nFROM [T]\nJOIN [J1] ON 1 = 1\nJOIN [J2] ON 2 = 2",
			query
				.join(fac.table("J1")).on(c1)
				.join(fac.table("J2")).on(c2)
			.toString()
		);
	}
}
