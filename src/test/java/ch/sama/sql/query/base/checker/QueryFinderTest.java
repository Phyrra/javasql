package ch.sama.sql.query.base.checker;

import static org.junit.Assert.*;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Function;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.tsql.dialect.TSqlQueryFactory;
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
                .select(fac.field("FIELD1"), fac.function("A(FIELD1)"), fac.field("FIELD2"), fac.function("B(FIELD2)"))
                .from(fac.table("TABLE"));

        List<Field> fields = finder.getSelected(query, Field.class);

        assertEquals(2, fields.size());
    }

    @Test
    public void getFunctions() {
        IQuery query = fac.create()
                .select(fac.field("FIELD1"), fac.function("A(FIELD1)"), fac.field("FIELD2"), fac.function("B(FIELD2)"), fac.function("C(FIELD3)"))
                .from(fac.table("TABLE"));

        List<Function> functions = finder.getSelected(query, Function.class);

        assertEquals(3, functions.size());
    }

    @Test
    public void getFromSource() {
        IQuery query = fac.create()
                .select(fac.field("FIELD"))
                .from(fac.table("TABLE1"), fac.table("TABLE2"));

        List<Table> tables = finder.getSources(query);

        assertEquals(2, tables.size());
    }

    @Test
    public void getJoinSources() {
        IQuery query = fac.create()
                .select(fac.field("FIELD"))
                .from(fac.table("TABLE1"))
                .join(fac.table("TABLE2")).on(Condition.eq(fac.numeric(1), fac.numeric(1)))
                .join(fac.table("TABLE3")).on(Condition.eq(fac.numeric(1), fac.numeric(1)));

        List<Table> tables = finder.getSources(query);

        assertEquals(3, tables.size());
    }
}
