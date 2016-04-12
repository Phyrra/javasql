package ch.sama.sql.dbo.result.obj;

import ch.sama.sql.dbo.connection.QueryExecutor;
import ch.sama.sql.dialect.sqlite.SqLiteQueryFactory;
import ch.sama.sql.dialect.sqlite.SqLiteValueFactory;
import ch.sama.sql.dialect.sqlite.connection.SQLiteConnection;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ObjectTransformerTest {
    private static double EPS = 1e-10;

    private static final SqLiteQueryFactory sql = new SqLiteQueryFactory();
    private static final SqLiteValueFactory value = sql.value();

    private QueryExecutor executor;
    private ObjectTransformer<TestObject> transformer;

    @Before
    public void loadExecutor() {
        executor = new QueryExecutor(new SQLiteConnection());
        transformer = new ObjectTransformer<TestObject>(TestObject.class);
    }

    @Test
    public void oneResult() {
        List<TestObject> results = executor.query(
                sql.query()
                        .select(value.string("string").as("sValue"), value.numeric(1).as("iValue"), value.numeric(1.1).as("dValue"))
                .getSql(),
                transformer
        );

        assertEquals(1, results.size());

        TestObject o = results.get(0);

        assertEquals("string", o.getStringVal());
        assertEquals(1, o.getIntVal());
        assertEquals(1.1, o.getDoubleVal(), EPS);
    }
}
