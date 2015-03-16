package ch.sama.sql.dbo;

import ch.sama.sql.query.base.IQueryFactory;
import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.tsql.dialect.TSqlQueryFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FieldTest {
    private static final IQueryFactory fac = new TSqlQueryFactory();
    private static final IQueryRenderer renderer = fac.renderer();

	public void nameOnly() {
		assertEquals("[FIELD]", new Field("FIELD").getString(renderer));
	}
	
	@Test
	public void withTableName() {
		assertEquals("[TABLE].[FIELD]", new Field("TABLE", "FIELD").getString(renderer));
	}

    @Test
    public void withTable() {
        assertEquals("[TABLE].[FIELD]", new Field(new Table("TABLE"), "FIELD").getString(renderer));
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
