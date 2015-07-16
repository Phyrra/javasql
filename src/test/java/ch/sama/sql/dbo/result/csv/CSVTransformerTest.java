package ch.sama.sql.dbo.result.csv;

import ch.sama.sql.csv.CSVSet;
import ch.sama.sql.csv.CSVWriter;
import ch.sama.sql.dbo.connection.QueryExecutor;
import ch.sama.sql.dialect.sqlite.SqLiteQueryFactory;
import ch.sama.sql.dialect.sqlite.SqLiteValueFactory;
import ch.sama.sql.dialect.sqlite.connection.SQLiteConnection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CSVTransformerTest {
    private static final SqLiteQueryFactory sql = new SqLiteQueryFactory();
    private static final SqLiteValueFactory value = sql.value();

    private QueryExecutor<CSVSet> executor;

    @Before
    public void loadExecutor() {
        SQLiteConnection connection = new SQLiteConnection();
        executor = new QueryExecutor<CSVSet>(connection, new CSVTransformer());
    }

    @Test
    public void oneResult() {
        CSVSet result = executor.query(
                sql.query()
                        .select(value.numeric(1))
                .getSql()
        );

        assertEquals(1, result.size());
        assertEquals(true, result.hasTitle());
    }

    @Test
    public void multiResult() {
        CSVSet result = executor.query(
                sql.query()
                        .select(value.numeric(1))
                        .union()
                        .select(value.numeric(2))
                        .union()
                        .select(value.numeric(3))
                .getSql()
        );

        assertEquals(3, result.size());
    }

    @Test
    public void csvOutputSingleRow() {
        CSVSet result = executor.query(
                sql.query()
                        .select(value.numeric(1).as("f1"), value.string("Hello World").as("f2"))
                .getSql()
        );

        assertEquals(
                "\"f1\" \"f2\"\r\n" +
                "\"1\" \"Hello World\"",
                new CSVWriter().write(result)
        );
    }

    @Test
    public void csvOutputMultiRow() {
        CSVSet result = executor.query(
                sql.query()
                        .select(value.string("1.1").as("f1"), value.string("1.2").as("f2"))
                        .union()
                        .select(value.string("2.1").as("f2"), value.string("2.2").as("f2"))
                .getSql()
        );

        assertEquals(
                "\"f1\" \"f2\"\r\n" +
                "\"1.1\" \"1.2\"\r\n" +
                "\"2.1\" \"2.2\"",
                new CSVWriter().write(result)
        );
    }
}
