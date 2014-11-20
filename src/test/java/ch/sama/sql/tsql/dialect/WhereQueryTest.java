package ch.sama.sql.tsql.dialect;

import static org.junit.Assert.*;

import ch.sama.sql.query.base.*;
import org.junit.Test;

import ch.sama.sql.query.helper.Condition;

public class WhereQueryTest {
    private static final IQueryRenderer renderer = new TSqlQueryRenderer();
    private static final IValueFactory value = new TSqlValueFactory();
    private static final ISourceFactory source = new TSqlSourceFactory();
	private static final FromQuery query = new Query().select(value.field("F")).from(source.table("T"));
	private static final Condition cond = Condition.eq(value.numeric(1), value.numeric(1));
	
	@Test
	public void afterFrom() {
		assertEquals("SELECT [F]\nFROM [T]\nWHERE 1 = 1", query.where(cond).getSql(renderer));
	}
	
	@Test
	public void afterJoin() {
		assertEquals(
			"SELECT [F]\nFROM [T]\nJOIN [J] ON 2 = 2\nWHERE 1 = 1",
			query
                    .join(source.table("J"))
                            .on(Condition.eq(value.numeric(2), value.numeric(2)))
                    .where(cond)
            .getSql(renderer)
		);
	}
}
