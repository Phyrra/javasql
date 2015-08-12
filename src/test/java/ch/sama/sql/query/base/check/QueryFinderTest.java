package ch.sama.sql.query.base.check;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.dialect.tsql.TSqlQueryFactory;
import ch.sama.sql.dialect.tsql.TSqlSourceFactory;
import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import ch.sama.sql.dialect.tsql.query.TSqlCteQuery;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.JoinQuery;
import ch.sama.sql.query.base.SelectQuery;
import ch.sama.sql.query.helper.Condition;
import ch.sama.sql.query.helper.Function;
import ch.sama.sql.query.helper.Source;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class QueryFinderTest {
    private static final QueryFinder finder = new QueryFinder();
    private static final TSqlQueryFactory sql = new TSqlQueryFactory();
    private static final TSqlSourceFactory source = sql.source();
    private static final TSqlValueFactory value = sql.value();

    @Test
    public void findSelect() {
        IQuery query = sql.query().select(value.field("A")).from(source.table("T"));
        SelectQuery select = finder.get(query, SelectQuery.class);

        assertEquals(1, select.getValues().size());
    }

    @Test
    public void noFind() {
        IQuery query = sql.query().select(value.field("A")).from(source.table("T"));
        TSqlCteQuery cte = finder.get(query, TSqlCteQuery.class);

        assertEquals(null, cte);
    }

    @Test
    public void getJoins() {
        IQuery query = sql.query()
                .select(TSqlValueFactory.ALL)
                .from(source.table("T"))
                .join(source.table("A")).on(Condition.eq(value.numeric(1), value.numeric(1)))
                .join(source.table("B")).on(Condition.eq(value.numeric(1), value.numeric(1)));

        List<JoinQuery> joins = finder.getAll(query, JoinQuery.class);

        assertEquals(2, joins.size());
    }

    @Test
    public void getFields() {
        IQuery query = sql.query()
                .select(value.field("FIELD1"), value.plain("A(FIELD1)"), value.field("FIELD2"), value.plain("B(FIELD2)"))
                .from(source.table("TABLE"));

        List<Field> fields = finder.getValues(query, Field.class);

        assertEquals(2, fields.size());
    }

    @Test
    public void getFunctions() {
        IQuery query = sql.query()
                .select(value.field("FIELD1"), value.function("A", value.field("FIELD1")), value.field("FIELD2"), value.function("B", value.field("FIELD2")), value.function("C", value.field("FIELD3")))
                .from(source.table("TABLE"));

        List<Function> functions = finder.getValues(query, Function.class);

        assertEquals(3, functions.size());
    }

    @Test
    public void getFromSource() {
        IQuery query = sql.query()
                .select(value.field("FIELD"))
                .from(source.table("TABLE1"), source.table("TABLE2"));

        List<Table> sources = finder.getSources(query, Table.class);

        assertEquals(2, sources.size());
    }

    @Test
    public void getJoinSources() {
        IQuery query = sql.query()
                .select(value.field("FIELD"))
                .from(source.table("TABLE1"))
                .join(source.table("TABLE2")).on(Condition.eq(value.numeric(1), value.numeric(1)))
                .join(source.table("TABLE3")).on(Condition.eq(value.numeric(1), value.numeric(1)));

        List<Table> sources = finder.getSources(query, Table.class);

        assertEquals(3, sources.size());
    }

    @Test
    public void getQuerySources() {
        IQuery query = sql.query()
                .select(value.field("FIELD"))
                .from(source.table("TABLE1"), source.query(sql.query().select(value.numeric(1))));

        List<IQuery> sources = finder.getSources(query, IQuery.class);

        assertEquals(1, sources.size());
    }

    @Test
    public void getAllSources() {
        IQuery query = sql.query()
                .select(value.field("FIELD"))
                .from(source.table("TABLE1"), source.query(sql.query().select(value.numeric(1))));

        List<Source> sources = finder.getSources(query);

        assertEquals(2, sources.size());
    }
    
    @Test
    public void getAllJoinSources() {
        IQuery query = sql.query()
                .select(value.field("FIELD"))
                .from(source.table("TABLE1"))
                .join(source.table("TABLE2")).on(Condition.eq(value.numeric(1), value.numeric(1)))
                .join(source.query(sql.query().select(value.numeric(1)))).on(Condition.eq(value.numeric(1), value.numeric(1)));
        
        List<Source> sources = finder.getSources(query);
        
        assertEquals(3, sources.size());
    }
}
