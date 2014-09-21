package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.query.base.ValueFactory;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Value;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnionTest {
    private static final QueryFactory fac = new TSqlQueryFactory();
    private static final ValueFactory value = new TSqlValueFactory();
	
	@Test
	public void select() {
        assertEquals(
                "SELECT 1 UNION ALL\nSELECT 2",
                fac.create()
                        .select(value.numeric(1)).union()
                        .select(value.numeric(2))
                .toString()
        );
	}

    @Test
    public void from() {
        assertEquals(
                "SELECT [FIELD]\nFROM [TABLE1] UNION ALL\nSELECT [FIELD]\nFROM [TABLE2]",
                fac.create()
                        .select(value.field("FIELD")).from(fac.table("TABLE1")).union()
                        .select(value.field("FIELD")).from(fac.table("TABLE2"))
                .toString()
        );
    }

    @Test
    public void where() {
        Condition c = Condition.eq(value.field("FIELD"), value.numeric(1));

        assertEquals(
                "SELECT *\nFROM [TABLE1]\nWHERE [FIELD] = 1 UNION ALL\nSELECT *\nFROM [TABLE2]\nWHERE [FIELD] = 1",
                fac.create()
                        .select(value.value(Value.ALL)).from(fac.table("TABLE1")).where(c).union()
                        .select(value.value(Value.ALL)).from(fac.table("TABLE2")).where(c)
                .toString()
        );
    }

    @Test
    public void join() throws Exception {
        Condition c = Condition.eq(value.numeric(1), value.numeric(1));

        assertEquals(
                "SELECT *\nFROM [TABLE1]\nJOIN [TABLE2] ON 1 = 1 UNION ALL\nSELECT *\nFROM [TABLE3]",
                fac.create()
                        .select(value.value(Value.ALL)).from(fac.table("TABLE1")).join(fac.table("TABLE2")).on(c).union()
                        .select(value.value(Value.ALL)).from(fac.table("TABLE3"))
                .toString()
        );
    }

    @Test
    public void multiple() {
        assertEquals(
                "SELECT 1 UNION ALL\nSELECT 2 UNION ALL\nSELECT 3",
                fac.create()
                        .select(value.numeric(1)).union()
                        .select(value.numeric(2)).union()
                        .select(value.numeric(3))
                .toString()
        );
    }
}
