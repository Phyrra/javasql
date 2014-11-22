package ch.sama.sql.query.helper;

import static org.junit.Assert.*;

import java.util.Date;
import java.text.SimpleDateFormat;

import ch.sama.sql.query.base.*;
import ch.sama.sql.tsql.dialect.TSqlQueryFactory;
import org.junit.Test;

public class ValueTest {
    private static final IQueryFactory fac = new TSqlQueryFactory();
    private static final IQueryRenderer renderer = fac.renderer();
    private static final IValueFactory value = fac.value();
    private static final ISourceFactory source = fac.source();

	@Test
	public void string() {
		assertEquals("'hello'", value.string("hello").getString(renderer));
	}
	
	@Test
	public void stringEscape() {
		assertEquals("'hello ''john'''", value.string("hello 'john'").getString(renderer));
	}
	
	@Test
	public void integer() {
		assertEquals("1", value.numeric(1).getString(renderer));
	}
	
	@Test
	public void numeric() {
		assertEquals("1.1", value.numeric(1.1f).getString(renderer));
		assertEquals("1.1", value.numeric(1.1d).getString(renderer));
	}
	
	@Test
	public void date() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Date d = format.parse("15.08.2014");
		assertEquals("CONVERT(datetime, '2014-08-15 00:00:00', 21)", value.date(d).getString(renderer));
	}
	
	@Test
	public void field() {
		assertEquals("[TABLE].[FIELD]", value.field("TABLE", "FIELD").getString(renderer));
	}

    @Test
    public void table() {
        assertEquals("[TABLE].*", value.table("TABLE").getString(renderer));
    }
	
	@Test
	public void nameAlias() {
		assertEquals("[NAME] AS [ALIAS]", value.field("NAME").as("ALIAS").getString(renderer));
	}
	
	@Test
	public void subQuery() {
		assertEquals("(\nSELECT 1\n) AS [ALIAS]", source.query(fac.query().select(value.numeric(1))).as("ALIAS").getString(renderer));
	}
	
	@Test
	public void function() {
		assertEquals("COUNT(*)", value.function("COUNT(*)").getString(renderer));
	}
	
	@Test
	public void selectFunction() {
		assertEquals(
                "SELECT COUNT(*) AS [_COUNT]\nFROM [TABLE]",
                fac.query()
                        .select(
                                value.function("COUNT(*)").as("_COUNT")
                        )
                        .from(source.table("TABLE"))
                .getSql()
		);
	}

    @Test
    public void nullValue() {
        assertEquals(
                "SELECT NULL",
                fac.query()
                        .select(
                                value.value(Value.NULL)
                        )
                .getSql()
        );
    }

    @Test
    public void allValue() {
        assertEquals(
                "SELECT *",
                fac.query()
                        .select(
                                value.value(Value.ALL)
                        )
                .getSql()
        );
    }
}
