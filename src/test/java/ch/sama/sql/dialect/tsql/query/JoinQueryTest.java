package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.query.base.FromQuery;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.dialect.tsql.TSqlQueryFactory;
import ch.sama.sql.dialect.tsql.TSqlSourceFactory;
import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JoinQueryTest {
    private static final TSqlQueryFactory sql = new TSqlQueryFactory();
    private static final TSqlValueFactory value = sql.value();
    private static final TSqlSourceFactory source = sql.source();

	private static final FromQuery query = sql.query().select(value.field("F")).from(source.table("T"));
    private static final ICondition cond = Condition.eq(value.numeric(1), value.numeric(1));
	
	@Test
	public void single() {
		assertEquals(
                "SELECT [F]\nFROM [T]\nJOIN [J] ON 1 = 1",
                query.join(source.table("J")).on(cond).getSql()
        );
	}

    @Test
    public void alias() {
        assertEquals(
                "SELECT [F]\nFROM [T]\nJOIN [J] AS [A] ON 1 = 1",
                query.join(source.table("J").as("A")).on(cond).getSql()
        );
    }
	
	@Test
	public void multiple() {
		assertEquals(
                "SELECT [F]\nFROM [T]\nJOIN [J1] ON 1 = 1\nJOIN [J2] ON 2 = 2",
                query
                        .join(source.table("J1")).on(cond)
                        .join(source.table("J2")).on(Condition.eq(value.numeric(2), value.numeric(2)))
                .getSql()
		);
	}

    @Test
    public void query() {
        assertEquals(
                "SELECT [F]\nFROM [T]\nJOIN (\nSELECT [F]\nFROM [T]\n) AS [T] ON 1 = 1",
                query
                        .join(
                                source.query(query).as("T")
                        ).on(cond)
                .getSql()
        );
    }
    
    @Test
    public void leftJoin() {
        assertEquals(
                "SELECT [F]\nFROM [T]\nLEFT JOIN [J] ON 1 = 1",
                query
                        .join(source.table("J")).left().on(cond)
                .getSql()
        );
    }

    @Test
    public void rightJoin() {
        assertEquals(
                "SELECT [F]\nFROM [T]\nRIGHT JOIN [J] ON 1 = 1",
                query
                        .join(source.table("J")).right().on(cond)
                .getSql()
        );
    }

    @Test
    public void innerJoin() {
        assertEquals(
                "SELECT [F]\nFROM [T]\nINNER JOIN [J] ON 1 = 1",
                query
                        .join(source.table("J")).inner().on(cond)
                .getSql()
        );
    }

    @Test
    public void fullJoin() {
        assertEquals(
                "SELECT [F]\nFROM [T]\nFULL JOIN [J] ON 1 = 1",
                query
                        .join(source.table("J")).full().on(cond)
                .getSql()
        );
    }

    @Test
    public void crossJoin() {
        assertEquals(
                "SELECT [F]\nFROM [T]\nCROSS JOIN [J] ON 1 = 1",
                query
                        .join(source.table("J")).cross().on(cond)
                .getSql()
        );
    }
}