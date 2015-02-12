package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.base.FromQuery;
import ch.sama.sql.query.base.IQueryFactory;
import ch.sama.sql.query.base.ISourceFactory;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.condition.ICondition;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JoinQueryTest {
    private static final IQueryFactory fac = new TSqlQueryFactory();
    private static final IValueFactory value = fac.value();
    private static final ISourceFactory source = fac.source();
	private static final FromQuery query = fac.query().select(value.field("F")).from(source.table("T"));
	
	@Test
	public void single() {
		ICondition c = Condition.eq(value.numeric(1), value.numeric(1));
		assertEquals("SELECT [F]\nFROM [T]\nJOIN [J] ON 1 = 1", query.join(source.table("J")).on(c).getSql());
	}

    @Test
    public void alias() {
        ICondition c = Condition.eq(value.numeric(1), value.numeric(1));
        assertEquals("SELECT [F]\nFROM [T]\nJOIN [J] AS [A] ON 1 = 1", query.join(source.table("J").as("A")).on(c).getSql());
    }
	
	@Test
	public void multiple() {
		ICondition c1 = Condition.eq(value.numeric(1), value.numeric(1));
		ICondition c2 = Condition.eq(value.numeric(2), value.numeric(2));
		
		assertEquals(
                "SELECT [F]\nFROM [T]\nJOIN [J1] ON 1 = 1\nJOIN [J2] ON 2 = 2",
                query
                        .join(source.table("J1")).on(c1)
                        .join(source.table("J2")).on(c2)
                .getSql()
		);
	}

    @Test
    public void query() {
        ICondition c1 = Condition.eq(value.numeric(1), value.numeric(1));

        assertEquals(
                "SELECT [F]\nFROM [T]\nJOIN (\nSELECT [F]\nFROM [T]\n) AS [T] ON 1 = 1",
                query
                        .join(
                                source.query(query).as("T")
                        ).on(c1)
                .getSql()
        );
    }
    
    @Test
    public void leftJoin() {
        ICondition c1 = Condition.eq(value.numeric(1), value.numeric(1));
        
        assertEquals(
                "SELECT [F]\nFROM [T]\nLEFT JOIN [J] ON 1 = 1",
                query
                        .join(source.table("J")).left().on(c1)
                .getSql()
        );
    }

    @Test
    public void rightJoin() {
        ICondition c1 = Condition.eq(value.numeric(1), value.numeric(1));

        assertEquals(
                "SELECT [F]\nFROM [T]\nRIGHT JOIN [J] ON 1 = 1",
                query
                        .join(source.table("J")).right().on(c1)
                .getSql()
        );
    }
}
