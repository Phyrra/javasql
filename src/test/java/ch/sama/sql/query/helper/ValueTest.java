package ch.sama.sql.query.helper;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.dialect.tsql.TSqlQueryFactory;
import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import ch.sama.sql.dialect.tsql.type.TYPE;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ValueTest {
    private static final TSqlQueryFactory sql = new TSqlQueryFactory();
    private static final TSqlQueryRenderer renderer = sql.renderer();
    private static final TSqlValueFactory value = sql.value();

	@Test
	public void string() {
		assertEquals("'hello'", renderer.render(value.string("hello")));
	}
	
	@Test
	public void stringEscape() {
		assertEquals("'hello ''john'''", renderer.render(value.string("hello 'john'")));
	}
	
	@Test
	public void integer() {
		assertEquals("1", renderer.render(value.numeric(1)));
	}
	
	@Test
	public void numeric() {
		assertEquals("1.1", renderer.render(value.numeric(1.1f)));
		assertEquals("1.1", renderer.render(value.numeric(1.1d)));
	}
    
    @Test
    public void bool() {
        assertEquals("1", renderer.render(value.bool(true)));
        assertEquals("0", renderer.render(value.bool(false)));
    }
	
	@Test
	public void date() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Date d = format.parse("15.08.2014");
		assertEquals("CONVERT(datetime, '2014-08-15 00:00:00', 21)", renderer.render(value.date(d)));
	}
	
    @Test
    public void field() {
        assertEquals("[dbo].[TABLE].[FIELD]", renderer.render(value.field(new Field(new Table("dbo", "TABLE"), "FIELD"))));
    }
    
	@Test
	public void stringField() {
		assertEquals("[FIELD]", renderer.render(value.field("FIELD")));
	}

    @Test
    public void stringTableField() {
        assertEquals("[TABLE].[FIELD]", renderer.render(value.field("TABLE", "FIELD")));
    }
    
    @Test
    public void tableField() {
        assertEquals("[dbo].[TABLE].[FIELD]", renderer.render(value.field(new Table("dbo", "TABLE"), "FIELD")));
    }

	@Test
    public void table() {
        assertEquals("[dbo].[TABLE].*", renderer.render(value.table(new Table("dbo", "TABLE"))));
    }

    @Test
    public void stringTable() {
        assertEquals("[TABLE].*", renderer.render(value.table("TABLE")));
    }

	@Test
	public void withAlias() {
		assertEquals("[FIELD] AS [ALIAS]", renderer.render(value.field("FIELD").as("ALIAS")));
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
		assertEquals("(\nSELECT 1\n) AS [QUERY]", renderer.render(value.query(sql.query().select(value.numeric(1))).as("QUERY")));
	}
	
	@Test
	public void function() {
		assertEquals("COUNT(*) AS [_COUNT]", renderer.render(value.function("COUNT", TSqlValueFactory.ALL).as("_COUNT")));
	}

	@Test
	public void nullValue() {
		assertEquals("NULL", renderer.render(TSqlValueFactory.NULL));
	}

	@Test
	public void allValue() {
		assertEquals("*", renderer.render(TSqlValueFactory.ALL));
	}
	
	@Test
	public void allTable() {
		assertEquals("[TABLE].*", renderer.render(value.table("TABLE")));
	}
    
    @Test
    public void combineTwo() {
        assertEquals("[TABLE].[FIELD1] + [TABLE].[FIELD2]", renderer.render(value.combine("+", value.field("TABLE", "FIELD1"), value.field("TABLE", "FIELD2"))));
    }
    
    @Test
    public void combineMultiple() {
        assertEquals("[TABLE].[FIELD1] + '_' + [TABLE].[FIELD2]", renderer.render(value.combine("+", value.field("TABLE", "FIELD1"), value.string("_"), value.field("TABLE", "FIELD2"))));
    }
    
    @Test
    public void combineWithAlias() {
        assertEquals("[TABLE].[FIELD1] + [TABLE].[FIELD2] AS [ALIAS]", renderer.render(value.combine("+", value.field("TABLE", "FIELD1"), value.field("TABLE", "FIELD2")).as("ALIAS")));
    }
    
    @Test
    public void bracket() {
        assertEquals("([TABLE].[FIELD])", renderer.render(value.bracket(value.field("TABLE", "FIELD"))));
    }
    
    @Test
    public void types() {
        assertEquals("int", renderer.render(value.type(TYPE.INT_TYPE)));
    }

    @Test
    public void variable() {
        assertEquals("@VAR", renderer.render(value.variable("VAR")));
    }

    @Test (expected = IllegalIdentifierException.class)
    public void badVariable() {
        value.variable("@VAR");
    }
}