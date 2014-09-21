package ch.sama.sql.tsql.dialect;

import static org.junit.Assert.*;

import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.query.base.ValueFactory;
import org.junit.Test;

import ch.sama.sql.query.base.SelectQuery;

public class FromQueryTest {
    private static final QueryFactory fac = new TSqlQueryFactory();
    private static final ValueFactory value = new TSqlValueFactory();
	private static final SelectQuery query = fac.create().select(value.field("F"));
	
	@Test
	public void single() {
		assertEquals(
                "SELECT [F]\nFROM [A]",
                query
                        .from(fac.table("A"))
                .toString()
        );
	}
	
	@Test
	public void multi() {
		assertEquals(
                "SELECT [F]\nFROM [A], [B]",
                query
                        .from(fac.table("A"), fac.table("B"))
                .toString()
        );
	}

    @Test
    public void query() {
        assertEquals(
                "SELECT [F]\nFROM (\nSELECT [F]\nFROM [A]\n)",
                query
                        .from(fac.query(
                                query.from(fac.table("A"))
                        ))
                .toString()
        );
    }
}
