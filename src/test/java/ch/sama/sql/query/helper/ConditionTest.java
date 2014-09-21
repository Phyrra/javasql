package ch.sama.sql.query.helper;

import static org.junit.Assert.*;

import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.query.base.ValueFactory;
import ch.sama.sql.tsql.dialect.TSqlQueryFactory;
import ch.sama.sql.tsql.dialect.TSqlValueFactory;
import org.junit.Test;

import ch.sama.sql.tsql.dialect.TSqlConditionParser;

public class ConditionTest {
    private static final QueryFactory fac = new TSqlQueryFactory();
    private static final ValueFactory value = new TSqlValueFactory();
	private static final ConditionParser parser = new TSqlConditionParser();
	
	@Test
	public void eq() {
		Condition c = Condition.eq(value.numeric(1), value.numeric(2));
		assertEquals("1 = 2", c.toString(parser));
	}
	
	@Test
	public void neq() {
		Condition c = Condition.neq(value.numeric(1), value.numeric(2));
		assertEquals("1 <> 2", c.toString(parser));
	}
	
	@Test
	public void like() {
		Condition c = Condition.like(value.string("hello"), value.string("hell%"));
		assertEquals("'hello' LIKE 'hell%'", c.toString(parser));
	}
	
	@Test
	public void not() {
		Condition c = Condition.not(Condition.eq(value.numeric(1), value.numeric(2)));
		assertEquals("NOT (1 = 2)", c.toString(parser));
	}
	
	@Test
	public void and() {
		Condition c = Condition.and(
				Condition.eq(value.numeric(1), value.numeric(2)),
				Condition.eq(value.numeric(3), value.numeric(4))
		);
		
		assertEquals("(1 = 2 AND 3 = 4)", c.toString(parser));
	}

    @Test
    public void multiAnd() {
        Condition c = Condition.and(
                Condition.eq(value.numeric(1), value.numeric(1)),
                Condition.eq(value.numeric(2), value.numeric(2)),
                Condition.eq(value.numeric(3), value.numeric(3))
        );

        assertEquals("(1 = 1 AND 2 = 2 AND 3 = 3)", c.toString(parser));
    }
	
	@Test
	public void or() {
		Condition c = Condition.or(
				Condition.eq(value.numeric(1), value.numeric(2)),
				Condition.eq(value.numeric(3), value.numeric(4))
		);
		
		assertEquals("(1 = 2 OR 3 = 4)", c.toString(parser));	
	}

    @Test
    public void multiOr() {
        Condition c = Condition.or(
                Condition.eq(value.numeric(1), value.numeric(1)),
                Condition.eq(value.numeric(2), value.numeric(2)),
                Condition.eq(value.numeric(3), value.numeric(3))
        );

        assertEquals("(1 = 1 OR 2 = 2 OR 3 = 3)", c.toString(parser));
    }

    @Test
     public void gt() {
        Condition c = Condition.gt(value.numeric(1), value.numeric(2));
        assertEquals("1 > 2", c.toString(parser));
    }

    @Test
    public void ge() {
        Condition c = Condition.ge(value.numeric(1), value.numeric(2));
        assertEquals("1 >= 2", c.toString(parser));
    }

    @Test
    public void lt() {
        Condition c = Condition.lt(value.numeric(1), value.numeric(2));
        assertEquals("1 < 2", c.toString(parser));
    }

    @Test
    public void le() {
        Condition c = Condition.le(value.numeric(1), value.numeric(2));
        assertEquals("1 <= 2", c.toString(parser));
    }

    @Test
    public void exists() {
        Condition c = Condition.exists(fac.create().select(value.numeric(1)));
        assertEquals("EXISTS (\nSELECT 1\n)", c.toString(parser));
    }

    @Test
    public void in() {
        Condition c = Condition.in(value.numeric(1), fac.create().select(value.numeric(1)));
        assertEquals("1 IN (\nSELECT 1\n)", c.toString(parser));
    }
}
