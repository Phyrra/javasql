package ch.sama.sql.dialect.tsql;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.FromQuery;
import ch.sama.sql.query.helper.Condition;

public class WhereQueryTest {
	private static final FromQuery query = new TSqlQuery().select(new TSqlValue(new Field("F"))).from(new Table("T"));
	private static final Condition cond = Condition.eq(new TSqlValue(1), new TSqlValue(1));
	
	@Test
	public void afterFrom() {
		assertEquals("SELECT F\nFROM T\nWHERE 1 = 1", query.where(cond).toString());
	}
	
	@Test
	public void afterJoin() {
		assertEquals(
			"SELECT F\nFROM T\nJOIN J ON 2 = 2\nWHERE 1 = 1",
			query
				.join(new Table("J"))
					.on(Condition.eq(new TSqlValue(2), new TSqlValue(2)))
				.where(cond)
            .toString()
		);
	}
}
