package ch.sama.sql.query.helper;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.sama.sql.tsql.dialect.TSqlConditionParser;
import ch.sama.sql.tsql.dialect.TSqlValue;

public class ConditionTest {
	private static final ConditionParser parser = new TSqlConditionParser();
	
	@Test
	public void eq() {
		Condition c = Condition.eq(new TSqlValue(1), new TSqlValue(2));
		assertEquals("1 = 2", parser.parse(c));
	}
	
	@Test
	public void neq() {
		Condition c = Condition.neq(new TSqlValue(1), new TSqlValue(2));
		assertEquals("1 <> 2", parser.parse(c));
	}
	
	@Test
	public void like() {
		Condition c = Condition.like(new TSqlValue("hello"), new TSqlValue("hell%"));
		assertEquals("'hello' LIKE 'hell%'", parser.parse(c));
	}
	
	@Test
	public void not() {
		Condition c = Condition.not(Condition.eq(new TSqlValue(1), new TSqlValue(2)));
		assertEquals("NOT (1 = 2)", parser.parse(c));
	}
	
	@Test
	public void and() {
		Condition c = Condition.and(
				Condition.eq(new TSqlValue(1), new TSqlValue(2)),
				Condition.eq(new TSqlValue(3), new TSqlValue(4))
		);
		
		assertEquals("(1 = 2 AND 3 = 4)", parser.parse(c));
	}
	
	@Test
	public void or() {
		Condition c = Condition.or(
				Condition.eq(new TSqlValue(1), new TSqlValue(2)),
				Condition.eq(new TSqlValue(3), new TSqlValue(4))
		);
		
		assertEquals("(1 = 2 OR 3 = 4)", parser.parse(c));	
	}
}
