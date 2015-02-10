package ch.sama.sql.query.helper;

import ch.sama.sql.query.base.IQueryFactory;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.condition.IConditionRenderer;
import ch.sama.sql.tsql.dialect.TSqlQueryFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConditionTest {
    private static final IQueryFactory fac = new TSqlQueryFactory();
    private static final IValueFactory value = fac.value();
    private static final IConditionRenderer parser = fac.condition();
	
	@Test
	public void eq() {
		ICondition c = Condition.eq(value.numeric(1), value.numeric(2));
		assertEquals("1 = 2", c.render(parser));
	}
	
	@Test
	public void neq() {
		ICondition c = Condition.neq(value.numeric(1), value.numeric(2));
		assertEquals("1 <> 2", c.render(parser));
	}
	
	@Test
	public void like() {
		ICondition c = Condition.like(value.string("hello"), value.string("hell%"));
		assertEquals("'hello' LIKE 'hell%'", c.render(parser));
	}
	
	@Test
	public void not() {
		ICondition c = Condition.not(Condition.eq(value.numeric(1), value.numeric(2)));
		assertEquals("NOT (1 = 2)", c.render(parser));
	}
	
	@Test
	public void and() {
		ICondition c = Condition.and(
				Condition.eq(value.numeric(1), value.numeric(2)),
				Condition.eq(value.numeric(3), value.numeric(4))
		);
		
		assertEquals("(1 = 2 AND 3 = 4)", c.render(parser));
	}

    @Test
    public void multiAnd() {
        ICondition c = Condition.and(
                Condition.eq(value.numeric(1), value.numeric(1)),
                Condition.eq(value.numeric(2), value.numeric(2)),
                Condition.eq(value.numeric(3), value.numeric(3))
        );

        assertEquals("(1 = 1 AND 2 = 2 AND 3 = 3)", c.render(parser));
    }
	
	@Test
	public void or() {
		ICondition c = Condition.or(
				Condition.eq(value.numeric(1), value.numeric(2)),
				Condition.eq(value.numeric(3), value.numeric(4))
		);
		
		assertEquals("(1 = 2 OR 3 = 4)", c.render(parser));
	}

    @Test
    public void multiOr() {
        ICondition c = Condition.or(
                Condition.eq(value.numeric(1), value.numeric(1)),
                Condition.eq(value.numeric(2), value.numeric(2)),
                Condition.eq(value.numeric(3), value.numeric(3))
        );

        assertEquals("(1 = 1 OR 2 = 2 OR 3 = 3)", c.render(parser));
    }

    @Test
     public void gt() {
        ICondition c = Condition.gt(value.numeric(1), value.numeric(2));
        assertEquals("1 > 2", c.render(parser));
    }

    @Test
    public void ge() {
        ICondition c = Condition.ge(value.numeric(1), value.numeric(2));
        assertEquals("1 >= 2", c.render(parser));
    }

    @Test
    public void lt() {
        ICondition c = Condition.lt(value.numeric(1), value.numeric(2));
        assertEquals("1 < 2", c.render(parser));
    }

    @Test
    public void le() {
        ICondition c = Condition.le(value.numeric(1), value.numeric(2));
        assertEquals("1 <= 2", c.render(parser));
    }

    @Test
    public void exists() {
        ICondition c = Condition.exists(fac.query().select(value.numeric(1)));
        assertEquals("EXISTS (\nSELECT 1\n)", c.render(parser));
    }

    @Test
    public void inQuery() {
        ICondition c = Condition.in(value.numeric(1), fac.query().select(value.numeric(1)));
        assertEquals("1 IN (\nSELECT 1\n)", c.render(parser));
    }
    
    @Test
    public void inList() {
        List<Value> values = new ArrayList<Value>();
        values.add(value.numeric(1));
        values.add(value.numeric(2));
        values.add(value.numeric(3));
        
        ICondition c = Condition.in(value.numeric(1), values);
        assertEquals("1 IN (\n1, 2, 3\n)", c.render(parser));
    }
    
    @Test
    public void isNull() {
        ICondition c = Condition.isNull(value.field("FIELD"));
        assertEquals("[FIELD] IS NULL", c.render(parser));
    }
}
