package ch.sama.sql.query.helper;

import static org.junit.Assert.*;

import java.util.Date;
import java.text.SimpleDateFormat;

import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.base.ISourceFactory;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.base.Query;
import ch.sama.sql.tsql.dialect.TSqlQueryRenderer;
import ch.sama.sql.tsql.dialect.TSqlSourceFactory;
import ch.sama.sql.tsql.dialect.TSqlValueFactory;
import org.junit.Test;

public class ValueTest {
    private static final IQueryRenderer renderer = new TSqlQueryRenderer();
    private static final IValueFactory value = new TSqlValueFactory();
    private static final ISourceFactory source = new TSqlSourceFactory();

	@Test
	public void string() {
		assertEquals("'hello'", value.string("hello").getString());
	}
	
	@Test
	public void stringEscape() {
		assertEquals("'hello ''john'''", value.string("hello 'john'").getString());
	}
	
	@Test
	public void integer() {
		assertEquals("1", value.numeric(1).getString());
	}
	
	@Test
	public void numeric() {
		assertEquals("1.1", value.numeric(1.1f).getString());
		assertEquals("1.1", value.numeric(1.1d).getString());
	}
	
	@Test
	public void date() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Date d = format.parse("15.08.2014");
		assertEquals("CONVERT(datetime, '2014-08-15 00:00:00', 21)", value.date(d).getString());
	}
	
	@Test
	public void field() {
		assertEquals("[TABLE].[FIELD]", value.field("TABLE", "FIELD").getString());
	}

    @Test
    public void table() {
        assertEquals("[TABLE].*", value.table("TABLE").getString());
    }
	
	@Test
	public void nameAlias() {
		assertEquals("[NAME] AS [ALIAS]", value.field("NAME").as("ALIAS").getString());
	}
	
	@Test
	public void subQuery() {
		assertEquals("(\nSELECT 1\n) AS [ALIAS]", source.query(new Query().select(value.numeric(1))).as("ALIAS").getString());
	}
	
	@Test
	public void function() {
		assertEquals("COUNT(*)", value.function("COUNT(*)").getString());
	}
	
	@Test
	public void selectFunction() {
		assertEquals(
			"SELECT COUNT(*) AS [_COUNT]\nFROM [TABLE]",
			new Query()
				    .select(
                            value.function("COUNT(*)").as("_COUNT")
				    )
				    .from(source.table("TABLE"))
			.getSql(renderer)
		);
	}

    @Test
    public void nullValue() {
        assertEquals(
            "SELECT NULL",
            new Query()
                    .select(
                            value.value(Value.NULL)
                    )
            .getSql(renderer)
        );
    }

    @Test
    public void allValue() {
        assertEquals(
            "SELECT *",
            new Query()
                    .select(
                            value.value(Value.ALL)
                    )
            .getSql(renderer)
        );
    }
}
