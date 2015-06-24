package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dialect.tsql.TSqlQueryFactory;
import ch.sama.sql.dialect.tsql.TSqlSourceFactory;
import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.condition.ICondition;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnionTest {
    private static final TSqlQueryFactory sql = new TSqlQueryFactory();
    private static final TSqlValueFactory value = sql.value();
    private static final TSqlSourceFactory source = sql.source();
    
    @Test
	public void select() {
        assertEquals(
                "SELECT 1 UNION ALL\nSELECT 2",
                sql.query()
                        .select(value.numeric(1)).union()
                        .select(value.numeric(2))
                .getSql()
        );
	}

    @Test
    public void from() {
        assertEquals(
                "SELECT [FIELD]\nFROM [TABLE1] UNION ALL\nSELECT [FIELD]\nFROM [TABLE2]",
                sql.query()
                        .select(value.field("FIELD")).from(source.table("TABLE1")).union()
                        .select(value.field("FIELD")).from(source.table("TABLE2"))
                .getSql()
        );
    }

    @Test
    public void where() {
        ICondition c = Condition.eq(value.field("FIELD"), value.numeric(1));

        assertEquals(
                "SELECT *\nFROM [TABLE1]\nWHERE [FIELD] = 1 UNION ALL\nSELECT *\nFROM [TABLE2]\nWHERE [FIELD] = 1",
                sql.query()
                        .select(TSqlValueFactory.ALL).from(source.table("TABLE1")).where(c).union()
                        .select(TSqlValueFactory.ALL).from(source.table("TABLE2")).where(c)
                .getSql()
        );
    }

    @Test
    public void join() throws Exception {
        ICondition c = Condition.eq(value.numeric(1), value.numeric(1));

        assertEquals(
                "SELECT *\nFROM [TABLE1]\nJOIN [TABLE2] ON 1 = 1 UNION ALL\nSELECT *\nFROM [TABLE3]",
                sql.query()
                        .select(TSqlValueFactory.ALL).from(source.table("TABLE1")).join(source.table("TABLE2")).on(c).union()
                        .select(TSqlValueFactory.ALL).from(source.table("TABLE3"))
                .getSql()
        );
    }

    @Test
    public void multiple() {
        assertEquals(
                "SELECT 1 UNION ALL\nSELECT 2 UNION ALL\nSELECT 3",
                sql.query()
                        .select(value.numeric(1)).union()
                        .select(value.numeric(2)).union()
                        .select(value.numeric(3))
                .getSql()
        );
    }
}