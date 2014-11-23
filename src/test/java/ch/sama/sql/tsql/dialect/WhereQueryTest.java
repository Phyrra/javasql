package ch.sama.sql.tsql.dialect;

import static org.junit.Assert.*;

import ch.sama.sql.query.base.*;
import ch.sama.sql.query.helper.condition.ICondition;
import org.junit.Test;

import ch.sama.sql.query.helper.Condition;

public class WhereQueryTest {
    private static final IQueryFactory fac = new TSqlQueryFactory();
    private static final IValueFactory value = fac.value();
    private static final ISourceFactory source = fac.source();
	private static final FromQuery query = fac.query().select(value.field("F")).from(source.table("T"));
	private static final ICondition cond = Condition.eq(value.numeric(1), value.numeric(1));
	
	@Test
	public void afterFrom() {
		assertEquals("SELECT [F]\nFROM [T]\nWHERE 1 = 1", query.where(cond).getSql());
	}
	
	@Test
	public void afterJoin() {
		assertEquals(
                "SELECT [F]\nFROM [T]\nJOIN [J] ON 2 = 2\nWHERE 1 = 1",
                query
                        .join(source.table("J"))
                                .on(Condition.eq(value.numeric(2), value.numeric(2)))
                        .where(cond)
                .getSql()
		);
	}
}
