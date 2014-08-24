package ch.sama.sql.query.base.checker;

import static org.junit.Assert.*;

import ch.sama.sql.dialect.tsql.TSqlQueryFactory;
import ch.sama.sql.query.base.CTEQuery;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.query.base.SelectQuery;
import ch.sama.sql.query.exception.BadParametersException;
import ch.sama.sql.query.helper.Value;
import org.junit.Test;

public class QueryFinderTest {
    private static final QueryFactory fac = new TSqlQueryFactory();
    private static final QueryFinder finder = new QueryFinder();

    @Test
    public void findSelect() {
        IQuery query = fac.create().select(fac.field("A")).from(fac.table("T"));
        SelectQuery select = finder.findQuery(query, SelectQuery.class);

        assertEquals(1, select.getValues().size());
    }

    @Test
    public void noFind() {
        IQuery query = fac.create().select(fac.field("A")).from(fac.table("T"));
        CTEQuery cte = finder.findQuery(query, CTEQuery.class);

        assertEquals(null, cte);
    }

    @Test(expected = BadParametersException.class)
    public void CTEinCTE() {
        IQuery query = fac.create()
                .with("CTE1").as(
                        fac.create().with("CTE2").select(fac.field("A")).from(fac.table("T"))
                ).select(fac.value(Value.ALL)).from(fac.table("CTE1"));
    }
}
