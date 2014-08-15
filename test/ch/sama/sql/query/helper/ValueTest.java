package ch.sama.sql.query.helper;

import static org.junit.Assert.*;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.junit.Test;

import ch.sama.sql.dbo.Field;
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
}
