package ch.sama.sql.query.helper;

import static org.junit.Assert.*;

import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.tsql.dialect.TSqlQueryFactory;
import org.junit.Test;

import ch.sama.sql.tsql.dialect.TSqlConditionParser;

public class ConditionTest {
    private static final QueryFactory fac = new TSqlQueryFactory();
	private static final ConditionParser parser = new TSqlConditionParser();
	
	@Test
	public void eq() {
		Condition c = Condition.eq(fac.numeric(1), fac.numeric(2));
		assertEquals("1 = 2", c.toString(parser));
	}
	
	@Test
	public void neq() {
		Condition c = Condition.neq(fac.numeric(1), fac.numeric(2));
		assertEquals("1 <> 2", c.toString(parser));
	}
	
	@Test
	public void like() {
		Condition c = Condition.like(fac.string("hello"), fac.string("hell%"));
		assertEquals("'hello' LIKE 'hell%'", c.toString(parser));
	}
	
	@Test
	public void not() {
		Condition c = Condition.not(Condition.eq(fac.numeric(1), fac.numeric(2)));
		assertEquals("NOT (1 = 2)", c.toString(parser));
	}
	
	@Test
	public void and() {
		Condition c = Condition.and(
				Condition.eq(fac.numeric(1), fac.numeric(2)),
				Condition.eq(fac.numeric(3), fac.numeric(4))
		);
		
		assertEquals("(1 = 2 AND 3 = 4)", c.toString(parser));
	}

    @Test
    public void multiAnd() {
        Condition c = Condition.and(
                Condition.eq(fac.numeric(1), fac.numeric(1)),
                Condition.eq(fac.numeric(2), fac.numeric(2)),
                Condition.eq(fac.numeric(3), fac.numeric(3))
        );

        assertEquals("(1 = 1 AND 2 = 2 AND 3 = 3)", c.toString(parser));
    }
	
	@Test
	public void or() {
		Condition c = Condition.or(
				Condition.eq(fac.numeric(1), fac.numeric(2)),
				Condition.eq(fac.numeric(3), fac.numeric(4))
		);
		
		assertEquals("(1 = 2 OR 3 = 4)", c.toString(parser));	
	}

    @Test
    public void multiOr() {
        Condition c = Condition.or(
                Condition.eq(fac.numeric(1), fac.numeric(1)),
                Condition.eq(fac.numeric(2), fac.numeric(2)),
                Condition.eq(fac.numeric(3), fac.numeric(3))
        );

        assertEquals("(1 = 1 OR 2 = 2 OR 3 = 3)", c.toString(parser));
    }

    @Test
     public void gt() {
        Condition c = Condition.gt(fac.numeric(1), fac.numeric(2));
        assertEquals("1 > 2", c.toString(parser));
    }

    @Test
    public void ge() {
        Condition c = Condition.ge(fac.numeric(1), fac.numeric(2));
        assertEquals("1 >= 2", c.toString(parser));
    }

    @Test
    public void lt() {
        Condition c = Condition.lt(fac.numeric(1), fac.numeric(2));
        assertEquals("1 < 2", c.toString(parser));
    }

    @Test
    public void le() {
        Condition c = Condition.le(fac.numeric(1), fac.numeric(2));
        assertEquals("1 <= 2", c.toString(parser));
    }

    @Test
    public void exists() {
        Condition c = Condition.exists(fac.create().select(fac.numeric(1)));
        assertEquals("EXISTS (\nSELECT 1\n)", c.toString(parser));
    }

    @Test
    public void in() {
        Condition c = Condition.in(fac.numeric(1), fac.create().select(fac.numeric(1)));
        assertEquals("1 IN (\nSELECT 1\n)", c.toString(parser));
    }
}
