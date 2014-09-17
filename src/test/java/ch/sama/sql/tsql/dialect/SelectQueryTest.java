package ch.sama.sql.tsql.dialect;

import static org.junit.Assert.*;

import ch.sama.sql.query.base.QueryFactory;
import org.junit.Test;

public class SelectQueryTest {
    private static final QueryFactory fac = new TSqlQueryFactory();
	
	@Test
	public void single() {
		assertEquals(
                "SELECT [A]",
                fac.create()
                        .select(fac.field("A"))
                .toString()
        );
	}
	
	@Test
	public void multi() {
		assertEquals(
                "SELECT [A], [B]",
                fac.create()
                        .select(fac.field("A"), fac.field("B"))
                .toString()
        );
	}
	
	@Test
	public void top() {
		assertEquals(
                "SELECT TOP 10 [A]",
                fac.create()
                        .select(fac.field("A")).top(10)
                .toString()
        );
	}
}
