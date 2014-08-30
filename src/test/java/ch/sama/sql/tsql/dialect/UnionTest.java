package ch.sama.sql.tsql.dialect;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Function;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.Query;
import ch.sama.sql.query.helper.Condition;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnionTest {
	private static final Query query = new TSqlQuery();
	
	@Test
	public void select() {
        assertEquals("SELECT 1 UNION ALL\nSELECT 2", query.select(new TSqlValue(1)).union().select(new TSqlValue(2)).toString());
	}

    @Test
    public void from() {
        assertEquals(
                "SELECT [FIELD]\nFROM [TABLE1] UNION ALL\nSELECT [FIELD]\nFROM [TABLE2]",
                query
                        .select(new TSqlValue(new Field("FIELD"))).from(new Table("TABLE1")).union()
                        .select(new TSqlValue(new Field("FIELD"))).from(new Table("TABLE2"))
                .toString()
        );
    }

    @Test
    public void where() {
        Condition c = Condition.eq(new TSqlValue(new Field("FIELD")), new TSqlValue(1));

        assertEquals(
                "SELECT *\nFROM [TABLE1]\nWHERE [FIELD] = 1 UNION ALL\nSELECT *\nFROM [TABLE2]\nWHERE [FIELD] = 1",
                query
                        .select(new TSqlValue(new Function("*"))).from(new Table("TABLE1")).where(c).union()
                        .select(new TSqlValue(new Function("*"))).from(new Table("TABLE2")).where(c)
                .toString()
        );
    }

    @Test
    public void join() throws Exception {
        Condition c = Condition.eq(new TSqlValue(1), new TSqlValue(1));

        assertEquals(
                "SELECT *\nFROM [TABLE1]\nJOIN [TABLE2] ON 1 = 1 UNION ALL\nSELECT *\nFROM [TABLE3]",
                query
                        .select(new TSqlValue(new Function("*"))).from(new Table("TABLE1")).join(new Table("TABLE2")).on(c).union()
                        .select(new TSqlValue(new Function("*"))).from(new Table("TABLE3"))
                .toString()
        );
    }

    @Test
    public void multiple() {
        assertEquals(
                "SELECT 1 UNION ALL\nSELECT 2 UNION ALL\nSELECT 3",
                query
                        .select(new TSqlValue(1)).union()
                        .select(new TSqlValue(2)).union()
                        .select(new TSqlValue(3))
                .toString()
        );
    }
}
