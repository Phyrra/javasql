package ch.sama.sql.query.helper;

import static org.junit.Assert.*;

import java.util.Date;
import java.text.SimpleDateFormat;

import ch.sama.sql.query.base.*;
import ch.sama.sql.query.exception.IllegalIdentifierException;
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
		assertEquals("[FIELD]", value.field("FIELD").getString(renderer));
	}

    @Test
    public void tableField() {
        assertEquals("[TABLE].[FIELD]", value.field("TABLE", "FIELD").getString(renderer));
    }
	
	@Test
	public void withAlias() {
		assertEquals("[FIELD] AS [ALIAS]", value.field("FIELD").as("ALIAS").getString(renderer));
	}
	
	@Test (expected = IllegalIdentifierException.class)
	public void withBadAlias() {
		value.field("FIELD").as("'");
	}
	
	@Test
	public void subQuery() {
		assertEquals("(\nSELECT 1\n) AS [QUERY]", value.query(fac.query().select(value.numeric(1))).as("QUERY").getString(renderer));
	}
	
	@Test
	public void function() {
		assertEquals("COUNT(*) AS [_COUNT]", value.function("COUNT", value.value(Value.ALL)).as("_COUNT").getString(renderer));
	}

	@Test
	public void nullValue() {
		assertEquals("NULL", value.value(Value.NULL).getString(renderer));
	}

	@Test
	public void allValue() {
		assertEquals("*", value.value(Value.ALL).getString(renderer));
	}
	
	@Test
	public void allTable() {
		assertEquals("[TABLE].*", value.table("TABLE").getString(renderer));
	}
}
