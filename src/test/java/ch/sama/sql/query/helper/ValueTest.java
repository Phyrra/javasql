package ch.sama.sql.query.helper;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.IQueryFactory;
import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.tsql.type.TYPE;
import ch.sama.sql.tsql.dialect.TSqlQueryFactory;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ValueTest {
    private static final IQueryFactory fac = new TSqlQueryFactory();
    private static final IQueryRenderer renderer = fac.renderer();
    private static final IValueFactory value = fac.value();

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
        assertEquals("[dbo].[TABLE].[FIELD]", value.field(new Field(new Table("dbo", "TABLE"), "FIELD")).getString(renderer));
    }
    
	@Test
	public void stringField() {
		assertEquals("[FIELD]", value.field("FIELD").getString(renderer));
	}

    @Test
    public void stringTableField() {
        assertEquals("[TABLE].[FIELD]", value.field("TABLE", "FIELD").getString(renderer));
    }
    
    @Test
    public void tableField() {
        assertEquals("[dbo].[TABLE].[FIELD]", value.field(new Table("dbo", "TABLE"), "FIELD").getString(renderer));
    }
	
	@Test
	public void withAlias() {
		assertEquals("[FIELD] AS [ALIAS]", value.field("FIELD").as("ALIAS").getString(renderer));
	}

    @Test
    public void aliasClone() {
        Value vo = value.field("FIELD");
        Value vc = vo.as("ALIAS");

        assertEquals(null, vo.getAlias());
        assertEquals("ALIAS", vc.getAlias());
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
    
    @Test
    public void combineTwo() {
        assertEquals("[TABLE].[FIELD1] + [TABLE].[FIELD2]", value.combine("+", value.field("TABLE", "FIELD1"), value.field("TABLE", "FIELD2")).getString(renderer));
    }
    
    @Test
    public void combineMultiple() {
        assertEquals("[TABLE].[FIELD1] + '_' + [TABLE].[FIELD2]", value.combine("+", value.field("TABLE", "FIELD1"), value.string("_"), value.field("TABLE", "FIELD2")).getString(renderer));
    }
    
    @Test
    public void combineWithAlias() {
        assertEquals("[TABLE].[FIELD1] + [TABLE].[FIELD2] AS [ALIAS]", value.combine("+", value.field("TABLE", "FIELD1"), value.field("TABLE", "FIELD2")).as("ALIAS").getString(renderer));
    }
    
    @Test
    public void bracket() {
        assertEquals("([TABLE].[FIELD])", value.bracket(value.field("TABLE", "FIELD")).getString(renderer));
    }
    
    @Test
    public void types() {
        assertEquals("int", value.type(TYPE.INT_TYPE).getString(renderer));
    }
}
