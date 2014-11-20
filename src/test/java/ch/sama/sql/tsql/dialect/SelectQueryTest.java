package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.base.Query;
import org.junit.Test;

import static org.junit.Assert.*;

public class SelectQueryTest {
    private static final IQueryRenderer renderer = new TSqlQueryRenderer();
    private static final IValueFactory value = new TSqlValueFactory();
	
	@Test
	public void single() {
		assertEquals(
                "SELECT [A]",
                new Query()
                        .select(value.field("A"))
                .getSql(renderer)
        );
	}
	
	@Test
	public void multi() {
		assertEquals(
                "SELECT [A], [B]",
                new Query()
                        .select(value.field("A"), value.field("B"))
                .getSql(renderer)
        );
	}
	
	@Test
	public void top() {
		assertEquals(
                "SELECT TOP 10 [A]",
                new Query()
                        .select(value.field("A")).top(10)
                .getSql(renderer)
        );
	}
}
