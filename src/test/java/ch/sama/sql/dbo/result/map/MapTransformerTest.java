package ch.sama.sql.dbo.result.map;

import ch.sama.sql.dbo.connection.QueryExecutor;
import ch.sama.sql.dialect.sqlite.SqLiteQueryFactory;
import ch.sama.sql.dialect.sqlite.SqLiteValueFactory;
import ch.sama.sql.dialect.sqlite.connection.SQLiteConnection;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MapTransformerTest {
    private static final SqLiteQueryFactory sql = new SqLiteQueryFactory();
    private static final SqLiteValueFactory value = sql.value();

    private QueryExecutor<List<MapResult>> executor;

    @Before
    public void loadExecutor() {
        SQLiteConnection connection = new SQLiteConnection();
        executor = new QueryExecutor<List<MapResult>>(connection, new MapTransformer());
    }

    @Test
    public void oneResult() {
        List<MapResult> results = executor.query(
                sql.query()
                        .select(value.numeric(1))
                .getSql()
        );

        assertEquals(1, results.size());
    }

    @Test
    public void multiResult() {
        List<MapResult> results = executor.query(
                sql.query()
                        .union(
                                sql.query().select(value.numeric(1)),
                                sql.query().select(value.numeric(2)),
                                sql.query().select(value.numeric(3))
                        )
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
                        .select(SqLiteValueFactory.NULL.as("f1"))
                .getSql()
        ).get(0);

        assertEquals(null, result.get("f1"));
    }
}
