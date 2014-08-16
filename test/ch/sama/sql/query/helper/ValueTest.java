package ch.sama.sql.query.helper;

import static org.junit.Assert.*;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.junit.Test;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Function;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.dialect.tsql.TSqlQuery;
import ch.sama.sql.dialect.tsql.TSqlValue;

public class ValueTest {
	@Test
	public void string() {
		assertEquals("'hello'", new TSqlValue("hello").toString());
	}
	
	@Test
	public void stringEscape() {
		assertEquals("'hello ''john'''", new TSqlValue("hello 'john'").toString());
	}
	
	@Test
	public void integer() {
		assertEquals("1", new TSqlValue(1).toString());
	}
	
	@Test
	public void numeric() {
		assertEquals("1.1", new TSqlValue(1.1f).toString());
		assertEquals("1.1", new TSqlValue(1.1d).toString());
	}
	
	@Test
	public void date() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Date d = format.parse("15.08.2014");
		assertEquals("CONVERT(datetime, '2014-08-15 00:00:00', 21)", new TSqlValue(d).toString());
	}
	
	@Test
	public void field() {
		assertEquals("TABLE.FIELD", new TSqlValue(new Field("TABLE", "FIELD")).toString());
	}
	
	@Test
	public void nameAlias() {
		assertEquals("NAME AS ALIAS", new TSqlValue(new Field("NAME")).as("ALIAS").toString());
	}
	
	@Test
	public void subQuery() {
		assertEquals("(\nSELECT 1\n) AS ALIAS", new TSqlValue(new TSqlQuery().select(new TSqlValue(1))).as("ALIAS").toString());
	}
	
	@Test
	public void function() {
		assertEquals("COUNT(*)", new TSqlValue(new Function("COUNT(*)")).toString());
	}
	
	@Test
	public void selectFunction() {
		assertEquals(
			"SELECT COUNT(*) AS _COUNT\nFROM TABLE",
			new TSqlQuery()
				.select(
					new TSqlValue(new Function("COUNT(*)")).as("_COUNT")
				)
				.from(new Table("TABLE"))
			.toString()
		);
	}
}
