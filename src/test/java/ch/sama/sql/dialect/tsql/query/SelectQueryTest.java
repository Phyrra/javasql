package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dialect.tsql.TSqlQueryBuilder;
import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class SelectQueryTest {
    private static final TSqlQueryBuilder sql = new TSqlQueryBuilder();
    private static final TSqlValueFactory value = sql.value();
	
	@Test
	public void single() {
		assertEquals(
                "SELECT [A]",
                sql.query()
                        .select(value.field("A"))
                .getSql()
        );
	}
	
	@Test
	public void multi() {
		assertEquals(
                "SELECT [A], [B]",
                sql.query()
                        .select(value.field("A"), value.field("B"))
                .getSql()
        );
	}
	
	@Test
	public void top() {
		assertEquals(
                "SELECT TOP 10 [A]",
                sql.query()
                        .select(value.field("A")).top(10)
                .getSql()
        );
	}
}