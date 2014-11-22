package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.base.IQueryFactory;
import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.base.IValueFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class SelectQueryTest {
    private static final IQueryFactory fac = new TSqlQueryFactory();
    private static final IQueryRenderer renderer = fac.renderer();
    private static final IValueFactory value = fac.value();
	
	@Test
	public void single() {
		assertEquals(
                "SELECT [A]",
                fac.query()
                        .select(value.field("A"))
                .getSql()
        );
	}
	
	@Test
	public void multi() {
		assertEquals(
                "SELECT [A], [B]",
                fac.query()
                        .select(value.field("A"), value.field("B"))
                .getSql()
        );
	}
	
	@Test
	public void top() {
		assertEquals(
                "SELECT TOP 10 [A]",
                fac.query()
                        .select(value.field("A")).top(10)
                .getSql()
        );
	}
}
