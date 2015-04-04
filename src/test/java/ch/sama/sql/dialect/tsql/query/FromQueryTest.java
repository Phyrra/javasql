package ch.sama.sql.dialect.tsql.query;

import static org.junit.Assert.*;

import ch.sama.sql.query.base.SelectQuery;
import ch.sama.sql.dialect.tsql.TSqlQueryBuilder;
import ch.sama.sql.dialect.tsql.TSqlSourceFactory;
import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import org.junit.Test;

public class FromQueryTest {
    private static final TSqlQueryBuilder sql = new TSqlQueryBuilder();
    private static final TSqlValueFactory value = sql.value();
    private static final TSqlSourceFactory source = sql.source();

	private static final SelectQuery query = sql.query().select(value.field("F"));
	
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
