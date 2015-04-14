package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.query.base.FromQuery;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.dialect.tsql.TSqlQueryFactory;
import ch.sama.sql.dialect.tsql.TSqlSourceFactory;
import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WhereQueryTest {
    private static final TSqlQueryFactory sql = new TSqlQueryFactory();
    private static final TSqlValueFactory value = sql.value();
    private static final TSqlSourceFactory source = sql.source();

	private static final FromQuery query = sql.query().select(value.field("F")).from(source.table("T"));
	private static final ICondition cond = Condition.eq(value.numeric(1), value.numeric(1));
	
	@Test
	public void afterFrom() {
		assertEquals(
				"SELECT [F]\nFROM [T]\nWHERE 1 = 1",
				query.where(cond).getSql()
		);
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
