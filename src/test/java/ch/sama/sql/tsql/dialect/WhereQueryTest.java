package ch.sama.sql.tsql.dialect;

import static org.junit.Assert.*;

import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.query.base.ValueFactory;
import org.junit.Test;

import ch.sama.sql.query.base.FromQuery;
import ch.sama.sql.query.helper.Condition;

public class WhereQueryTest {
    private static final QueryFactory fac = new TSqlQueryFactory();
    private static final ValueFactory value = new TSqlValueFactory();
	private static final FromQuery query = new TSqlQuery().select(value.field("F")).from(fac.table("T"));
	private static final Condition cond = Condition.eq(value.numeric(1), value.numeric(1));
	
	@Test
	public void afterFrom() {
		assertEquals("SELECT [F]\nFROM [T]\nWHERE 1 = 1", query.where(cond).toString());
	}
	
	@Test
	public void afterJoin() {
		assertEquals(
			"SELECT [F]\nFROM [T]\nJOIN [J] ON 2 = 2\nWHERE 1 = 1",
			query
                    .join(fac.table("J"))
                            .on(Condition.eq(value.numeric(2), value.numeric(2)))
                    .where(cond)
            .toString()
		);
	}
}
