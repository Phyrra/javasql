package ch.sama.sql.query.base.checker;

import static org.junit.Assert.*;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Function;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.tsql.dialect.TSqlQueryFactory;
import ch.sama.sql.query.base.*;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.tsql.dialect.TSqlValueFactory;
import org.junit.Test;

import java.util.List;

public class QueryFinderTest {
    private static final QueryFactory fac = new TSqlQueryFactory();
    private static final ValueFactory value = new TSqlValueFactory();
    private static final QueryFinder finder = new QueryFinder();

    @Test
    public void findSelect() {
        IQuery query = fac.create().select(value.field("A")).from(fac.table("T"));
        SelectQuery select = finder.get(query, SelectQuery.class);

        assertEquals(1, select.getValues().size());
    }

    @Test
    public void noFind() {
        IQuery query = fac.create().select(value.field("A")).from(fac.table("T"));
        CTEQuery cte = finder.get(query, CTEQuery.class);

        assertEquals(null, cte);
    }

    @Test(expected = BadSqlException.class)
    public void CTEinCTE() {
        IQuery query = fac.create()
                .with("CTE1").as(
                        fac.create().with("CTE2").select(value.field("A")).from(fac.table("T"))
                ).select(value.value(Value.ALL)).from(fac.table("CTE1"));
    }

    @Test
    public void getJoins() {
        IQuery query = fac.create()
                .select(value.value(Value.ALL))
                .from(fac.table("T"))
                .join(fac.table("A")).on(Condition.eq(value.numeric(1), value.numeric(1)))
                .join(fac.table("B")).on(Condition.eq(value.numeric(1), value.numeric(1)));

        List<JoinQuery> joins = finder.getAll(query, JoinQuery.class);

        assertEquals(2, joins.size());
    }

    @Test
    public void getFields() {
        IQuery query = fac.create()
                .select(value.field("FIELD1"), value.function("A(FIELD1)"), value.field("FIELD2"), value.function("B(FIELD2)"))
                .from(fac.table("TABLE"));

        List<Field> fields = finder.getSelected(query, Field.class);

        assertEquals(2, fields.size());
    }

    @Test
    public void getFunctions() {
        IQuery query = fac.create()
                .select(value.field("FIELD1"), value.function("A(FIELD1)"), value.field("FIELD2"), value.function("B(FIELD2)"), value.function("C(FIELD3)"))
                .from(fac.table("TABLE"));

        List<Function> functions = finder.getSelected(query, Function.class);

        assertEquals(3, functions.size());
    }

    @Test
    public void getFromSource() {
        IQuery query = fac.create()
                .select(value.field("FIELD"))
                .from(fac.table("TABLE1"), fac.table("TABLE2"));

        List<Source> sources = finder.getSources(query);

        assertEquals(2, sources.size());
    }

    @Test
    public void getJoinSources() {
        IQuery query = fac.create()
                .select(value.field("FIELD"))
                .from(fac.table("TABLE1"))
                .join(fac.table("TABLE2")).on(Condition.eq(value.numeric(1), value.numeric(1)))
                .join(fac.table("TABLE3")).on(Condition.eq(value.numeric(1), value.numeric(1)));

        List<Source> sources = finder.getSources(query);

        assertEquals(3, sources.size());
    }
}
