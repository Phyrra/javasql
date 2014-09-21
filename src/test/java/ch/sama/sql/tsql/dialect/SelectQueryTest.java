package ch.sama.sql.tsql.dialect;

import static org.junit.Assert.*;

import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.query.base.ValueFactory;
import org.junit.Test;

public class SelectQueryTest {
    private static final QueryFactory fac = new TSqlQueryFactory();
    private static final ValueFactory value = new TSqlValueFactory();
	
	@Test
	public void single() {
		assertEquals(
                "SELECT [A]",
                fac.create()
                        .select(value.field("A"))
                .toString()
        );
	}
	
	@Test
	public void multi() {
		assertEquals(
                "SELECT [A], [B]",
                fac.create()
                        .select(value.field("A"), value.field("B"))
                .toString()
        );
	}
	
	@Test
	public void top() {
		assertEquals(
                "SELECT TOP 10 [A]",
                fac.create()
                        .select(value.field("A")).top(10)
                .toString()
        );
	}
}
