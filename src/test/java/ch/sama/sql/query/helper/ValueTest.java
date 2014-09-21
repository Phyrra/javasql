package ch.sama.sql.query.helper;

import static org.junit.Assert.*;

import java.util.Date;
import java.text.SimpleDateFormat;

import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.query.base.ValueFactory;
import ch.sama.sql.tsql.dialect.TSqlQueryFactory;
import ch.sama.sql.tsql.dialect.TSqlValueFactory;
import org.junit.Test;

public class ValueTest {
    public static final QueryFactory fac = new TSqlQueryFactory();
    private static final ValueFactory value = new TSqlValueFactory();

	@Test
	public void string() {
		assertEquals("'hello'", value.string("hello").toString());
	}
	
	@Test
	public void stringEscape() {
		assertEquals("'hello ''john'''", value.string("hello 'john'").toString());
	}
	
	@Test
	public void integer() {
		assertEquals("1", value.numeric(1).toString());
	}
	
	@Test
	public void numeric() {
		assertEquals("1.1", value.numeric(1.1f).toString());
		assertEquals("1.1", value.numeric(1.1d).toString());
	}
	
	@Test
	public void date() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Date d = format.parse("15.08.2014");
		assertEquals("CONVERT(datetime, '2014-08-15 00:00:00', 21)", value.date(d).toString());
	}
	
	@Test
	public void field() {
		assertEquals("[TABLE].[FIELD]", value.field("TABLE", "FIELD").toString());
	}

    @Test
    public void table() {
        assertEquals("[TABLE].*", value.table("TABLE").toString());
    }
	
	@Test
	public void nameAlias() {
		assertEquals("[NAME] AS [ALIAS]", value.field("NAME").as("ALIAS").toString());
	}
	
	@Test
	public void subQuery() {
		assertEquals("(\nSELECT 1\n) AS [ALIAS]", fac.query(fac.create().select(value.numeric(1))).as("ALIAS").toString());
	}
	
	@Test
	public void function() {
		assertEquals("COUNT(*)", value.function("COUNT(*)").toString());
	}
	
	@Test
	public void selectFunction() {
		assertEquals(
			"SELECT COUNT(*) AS [_COUNT]\nFROM [TABLE]",
			fac.create()
				    .select(
                            value.function("COUNT(*)").as("_COUNT")
				    )
				    .from(fac.table("TABLE"))
			.toString()
		);
	}

    @Test
    public void nullValue() {
        assertEquals(
            "SELECT NULL",
            fac.create()
                    .select(
                            value.value(Value.NULL)
                    )
            .toString()
        );
    }

    @Test
    public void allValue() {
        assertEquals(
            "SELECT *",
            fac.create()
                    .select(
                            value.value(Value.ALL)
                    )
            .toString()
        );
    }
}
