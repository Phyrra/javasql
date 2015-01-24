package ch.sama.sql.dbo;

import static org.junit.Assert.*;

import ch.sama.sql.query.base.IQueryFactory;
import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.tsql.dialect.TSqlQueryFactory;
import org.junit.Test;

public class FieldTest {
    private static final IQueryFactory fac = new TSqlQueryFactory();
    private static final IQueryRenderer renderer = fac.renderer();

	public void nameOnly() {
		assertEquals("[NAME]", new Field("NAME").getString(renderer));
	}
	
	@Test
	public void withTableName() {
		assertEquals("[TABLE].[NAME]", new Field("TABLE", "NAME").getString(renderer));
	}

    @Test
    public void withTable() {
        assertEquals("[TABLE].[NAME]", new Field(new Table("TABLE"), "NAME").getString(renderer));
    }
	
	@Test (expected = IllegalIdentifierException.class)
	public void badField() {
		new Field("'");		
	}
	
	@Test (expected = IllegalIdentifierException.class)
	public void badTable() {
		new Field("'", "FIELD");
	}
}
