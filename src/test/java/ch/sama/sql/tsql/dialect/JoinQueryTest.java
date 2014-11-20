package ch.sama.sql.tsql.dialect;

import static org.junit.Assert.*;

import ch.sama.sql.query.base.*;
import org.junit.Test;

import ch.sama.sql.query.helper.Condition;

public class JoinQueryTest {
    private static final IQueryRenderer renderer = new TSqlQueryRenderer();
    private static final IValueFactory value = new TSqlValueFactory();
    private static final ISourceFactory source = new TSqlSourceFactory();
	private static final FromQuery query = new Query().select(value.field("F")).from(source.table("T"));
	
	@Test
	public void single() {
		Condition c = Condition.eq(value.numeric(1), value.numeric(1));
		assertEquals("SELECT [F]\nFROM [T]\nJOIN [J] ON 1 = 1", query.join(source.table("J")).on(c).getSql(renderer));
	}

    @Test
    public void alias() {
        Condition c = Condition.eq(value.numeric(1), value.numeric(1));
        assertEquals("SELECT [F]\nFROM [T]\nJOIN [J] AS [A] ON 1 = 1", query.join(source.table("J").as("A")).on(c).getSql(renderer));
    }
	
	@Test
	public void multiple() {
		Condition c1 = Condition.eq(value.numeric(1), value.numeric(1));
		Condition c2 = Condition.eq(value.numeric(2), value.numeric(2));
		
		assertEquals(
                "SELECT [F]\nFROM [T]\nJOIN [J1] ON 1 = 1\nJOIN [J2] ON 2 = 2",
                query
                        .join(source.table("J1")).on(c1)
                        .join(source.table("J2")).on(c2)
                .getSql(renderer)
		);
	}

    @Test
    public void query() {
        Condition c1 = Condition.eq(value.numeric(1), value.numeric(1));

        assertEquals(
                "SELECT [F]\nFROM [T]\nJOIN (\nSELECT [F]\nFROM [T]\n) AS [T] ON 1 = 1",
                query
                        .join(
                                source.query(query).as("T")
                        ).on(c1)
                .getSql(renderer)
        );
    }
}
