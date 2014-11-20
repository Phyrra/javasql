package ch.sama.sql.tsql.dialect;

import static org.junit.Assert.*;

import ch.sama.sql.query.base.*;
import org.junit.Test;

public class FromQueryTest {
    private static final IQueryRenderer renderer = new TSqlQueryRenderer();
    private static final IValueFactory value = new TSqlValueFactory();
    private static final ISourceFactory source = new TSqlSourceFactory();
	private static final SelectQuery query = new Query().select(value.field("F"));
	
	@Test
	public void single() {
		assertEquals(
                "SELECT [F]\nFROM [A]",
                query
                        .from(source.table("A"))
                .getSql(renderer)
        );
	}
	
	@Test
	public void multi() {
		assertEquals(
                "SELECT [F]\nFROM [A], [B]",
                query
                        .from(source.table("A"), source.table("B"))
                .getSql(renderer)
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
                .getSql(renderer)
        );
    }
}
