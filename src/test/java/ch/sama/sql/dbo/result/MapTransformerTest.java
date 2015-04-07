package ch.sama.sql.dbo.result;

import ch.sama.sql.dbo.connection.QueryExecutor;
import ch.sama.sql.dialect.sqlite.SqLiteQueryBuilder;
import ch.sama.sql.dialect.sqlite.SqLiteValueFactory;
import ch.sama.sql.dialect.sqlite.connection.SQLiteConnection;
import ch.sama.sql.query.helper.Value;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapTransformerTest {
    private static final SqLiteQueryBuilder sql = new SqLiteQueryBuilder();
    private static final SqLiteValueFactory value = sql.value();

    private QueryExecutor<MapResultList> executor;

    @Before
    public void loadExecutor() {
        SQLiteConnection connection = new SQLiteConnection();
        executor = new QueryExecutor<MapResultList>(connection, new MapTransformer());
    }

    @Test
    public void oneResult() {
        MapResultList results = executor.query(
                sql.query()
                        .select(value.numeric(1))
                .getSql()
        );

        assertEquals(1, results.size());
    }

    @Test
    public void multiResult() {
        MapResultList results = executor.query(
                sql.query()
                        .select(value.numeric(1))
                        .union()
                        .select(value.numeric(2))
                        .union()
                        .select(value.numeric(3))
                .getSql()
        );

        assertEquals(3, results.size());
    }

    @Test
    public void numerics() {
        MapResult result = executor.query(
                sql.query()
                        .select(value.numeric(1).as("f1"), value.numeric(1.1).as("f2")) // Boolean == Int
                .getSql()
        ).get(0);

        assertEquals(Integer.class, result.get("f1").getClass());
        assertEquals(Double.class, result.get("f2").getClass());
    }

    @Test
    public void text() {
        MapResult result = executor.query(
                sql.query()
                        .select(value.string("Hello World").as("f1")) // Can't really test Clob
                .getSql()
        ).get(0);

        assertEquals(String.class, result.get("f1").getClass());
    }

    @Test
    public void date() {
        MapResult result = executor.query(
                sql.query()
                        .select(value.function("date", value.string("2015-04-02")).as("f1")) // SQLite does not have date type
                .getSql()
        ).get(0);

        assertEquals(String.class, result.get("f1").getClass());
    }

    @Test
    public void nothing() {
        MapResult result = executor.query(
                sql.query()
                        .select(value.value(Value.VALUE.NULL).as("f1"))
                .getSql()
        ).get(0);

        assertEquals(null, result.get("f1"));
    }
}
