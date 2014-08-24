package ch.sama.sql.query.base.checker;

import static org.junit.Assert.*;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dialect.tsql.TSqlQueryFactory;
import ch.sama.sql.query.base.*;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Value;
import org.junit.Test;

import java.util.List;

public class QueryFinderTest {
    private static final QueryFactory fac = new TSqlQueryFactory();
    private static final QueryFinder finder = new QueryFinder();

    @Test
    public void findSelect() {
        IQuery query = fac.create().select(fac.field("A")).from(fac.table("T"));
        SelectQuery select = finder.get(query, SelectQuery.class);

        assertEquals(1, select.getValues().size());
    }

    @Test
    public void noFind() {
        IQuery query = fac.create().select(fac.field("A")).from(fac.table("T"));
        CTEQuery cte = finder.get(query, CTEQuery.class);

        assertEquals(null, cte);
    }

    @Test(expected = BadSqlException.class)
    public void CTEinCTE() {
        IQuery query = fac.create()
                .with("CTE1").as(
                        fac.create().with("CTE2").select(fac.field("A")).from(fac.table("T"))
                ).select(fac.value(Value.ALL)).from(fac.table("CTE1"));
    }

    @Test
    public void getJoins() {
        IQuery query = fac.create()
                .select(fac.value(Value.ALL))
                .from(fac.table("T"))
                .join(fac.table("A")).on(Condition.eq(fac.numeric(1), fac.numeric(1)))
                .join(fac.table("B")).on(Condition.eq(fac.numeric(1), fac.numeric(1)));

        List<JoinQuery> joins = finder.getAll(query, JoinQuery.class);

        assertEquals(2, joins.size());
    }

    @Test
    public void getFields() {
        IQuery query = fac.create()
                .select(fac.field("FIELD1"), fac.field("FIELD2"))
                .from(fac.table("TABLE"));

        List<Field> fields = finder.getSelectedFields(query);

        assertEquals(2, fields.size());
    }
}
