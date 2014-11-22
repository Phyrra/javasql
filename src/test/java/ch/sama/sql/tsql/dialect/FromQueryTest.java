package ch.sama.sql.tsql.dialect;

import static org.junit.Assert.*;

import ch.sama.sql.query.base.IQueryFactory;
import ch.sama.sql.query.base.ISourceFactory;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.base.SelectQuery;
import org.junit.Test;

public class FromQueryTest {
    private static final IQueryFactory fac = new TSqlQueryFactory();
    private static final IValueFactory value = fac.value();
    private static final ISourceFactory source = fac.source();
	private static final SelectQuery query = fac.query().select(value.field("F"));
	
	@Test
	public void single() {
		assertEquals(
                "SELECT [F]\nFROM [A]",
                query
                        .from(source.table("A"))
                .getSql()
        );
	}
	
	@Test
	public void multi() {
		assertEquals(
                "SELECT [F]\nFROM [A], [B]",
                query
                        .from(source.table("A"), source.table("B"))
                .getSql()
        );
	}

    @Test
    public void query() {
        assertEquals(
                "SELECT [F]\nFROM (\nSELECT [F]\nFROM [A]\n)",
                query
                        .from(source.query(
                                query.from(source.table("A"))
                        ))
                .getSql()
        );
    }
}
