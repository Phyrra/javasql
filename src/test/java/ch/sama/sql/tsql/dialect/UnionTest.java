package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.base.ISourceFactory;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.base.Query;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Value;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnionTest {
    private static final IQueryRenderer renderer = new TSqlQueryRenderer();
    private static final IValueFactory value = new TSqlValueFactory();
    private static final ISourceFactory source = new TSqlSourceFactory();
	
	@Test
	public void select() {
        assertEquals(
                "SELECT 1 UNION ALL\nSELECT 2",
                new Query()
                        .select(value.numeric(1)).union()
                        .select(value.numeric(2))
                .getSql(renderer)
        );
	}

    @Test
    public void from() {
        assertEquals(
                "SELECT [FIELD]\nFROM [TABLE1] UNION ALL\nSELECT [FIELD]\nFROM [TABLE2]",
                new Query()
                        .select(value.field("FIELD")).from(source.table("TABLE1")).union()
                        .select(value.field("FIELD")).from(source.table("TABLE2"))
                .getSql(renderer)
        );
    }

    @Test
    public void where() {
        Condition c = Condition.eq(value.field("FIELD"), value.numeric(1));

        assertEquals(
                "SELECT *\nFROM [TABLE1]\nWHERE [FIELD] = 1 UNION ALL\nSELECT *\nFROM [TABLE2]\nWHERE [FIELD] = 1",
                new Query()
                        .select(value.value(Value.ALL)).from(source.table("TABLE1")).where(c).union()
                        .select(value.value(Value.ALL)).from(source.table("TABLE2")).where(c)
                .getSql(renderer)
        );
    }

    @Test
    public void join() throws Exception {
        Condition c = Condition.eq(value.numeric(1), value.numeric(1));

        assertEquals(
                "SELECT *\nFROM [TABLE1]\nJOIN [TABLE2] ON 1 = 1 UNION ALL\nSELECT *\nFROM [TABLE3]",
                new Query()
                        .select(value.value(Value.ALL)).from(source.table("TABLE1")).join(source.table("TABLE2")).on(c).union()
                        .select(value.value(Value.ALL)).from(source.table("TABLE3"))
                .getSql(renderer)
        );
    }

    @Test
    public void multiple() {
        assertEquals(
                "SELECT 1 UNION ALL\nSELECT 2 UNION ALL\nSELECT 3",
                new Query()
                        .select(value.numeric(1)).union()
                        .select(value.numeric(2)).union()
                        .select(value.numeric(3))
                .getSql(renderer)
        );
    }
}
