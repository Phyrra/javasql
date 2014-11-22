package ch.sama.sql.query.base.checker;

import static org.junit.Assert.*;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Function;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.base.*;
import ch.sama.sql.tsql.dialect.TSqlQueryFactory;
import org.junit.Test;

import java.util.List;

public class QueryFinderTest {
    private static final QueryFinder finder = new QueryFinder();
    private static final IQueryFactory fac = new TSqlQueryFactory();
    private static final ISourceFactory source = fac.source();
    private static final IValueFactory value = fac.value();

    @Test
    public void findSelect() {
        IQuery query = fac.query().select(value.field("A")).from(source.table("T"));
        SelectQuery select = finder.get(query, SelectQuery.class);

        assertEquals(1, select.getValues().size());
    }

    @Test
    public void noFind() {
        IQuery query = fac.query().select(value.field("A")).from(source.table("T"));
        CTEQuery cte = finder.get(query, CTEQuery.class);

        assertEquals(null, cte);
    }

    @Test
    public void getJoins() {
        IQuery query = fac.query()
                .select(value.value(Value.ALL))
                .from(source.table("T"))
                .join(source.table("A")).on(Condition.eq(value.numeric(1), value.numeric(1)))
                .join(source.table("B")).on(Condition.eq(value.numeric(1), value.numeric(1)));

        List<JoinQuery> joins = finder.getAll(query, JoinQuery.class);

        assertEquals(2, joins.size());
    }

    @Test
    public void getFields() {
        IQuery query = fac.query()
                .select(value.field("FIELD1"), value.function("A(FIELD1)"), value.field("FIELD2"), value.function("B(FIELD2)"))
                .from(source.table("TABLE"));

        List<Field> fields = finder.getSelected(query, Field.class);

        assertEquals(2, fields.size());
    }

    @Test
    public void getFunctions() {
        IQuery query = fac.query()
                .select(value.field("FIELD1"), value.function("A(FIELD1)"), value.field("FIELD2"), value.function("B(FIELD2)"), value.function("C(FIELD3)"))
                .from(source.table("TABLE"));

        List<Function> functions = finder.getSelected(query, Function.class);

        assertEquals(3, functions.size());
    }

    @Test
    public void getFromSource() {
        IQuery query = fac.query()
                .select(value.field("FIELD"))
                .from(source.table("TABLE1"), source.table("TABLE2"));

        List<Table> sources = finder.getSources(query, Table.class);

        assertEquals(2, sources.size());
    }

    @Test
    public void getJoinSources() {
        IQuery query = fac.query()
                .select(value.field("FIELD"))
                .from(source.table("TABLE1"))
                .join(source.table("TABLE2")).on(Condition.eq(value.numeric(1), value.numeric(1)))
                .join(source.table("TABLE3")).on(Condition.eq(value.numeric(1), value.numeric(1)));

        List<Table> sources = finder.getSources(query, Table.class);

        assertEquals(3, sources.size());
    }

    @Test
    public void getQuerySources() {
        IQuery query = fac.query()
                .select(value.field("FIELD"))
                .from(source.table("TABLE1"), source.query(fac.query().select(value.numeric(1))));

        List<IQuery> sources = finder.getSources(query, IQuery.class);

        assertEquals(1, sources.size());
    }

    @Test
    public void getAllSources() {
        IQuery query = fac.query()
                .select(value.field("FIELD"))
                .from(source.table("TABLE1"), source.query(fac.query().select(value.numeric(1))));

        List<Source> sources = finder.getSources(query);

        assertEquals(2, sources.size());
    }
}
