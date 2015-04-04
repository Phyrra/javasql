package ch.sama.sql.dbo;

import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.dialect.tsql.TSqlQueryBuilder;
import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FieldTest {
    private static final TSqlQueryBuilder sql = new TSqlQueryBuilder();
    private static final TSqlQueryRenderer renderer = sql.renderer();

	public void nameOnly() {
		assertEquals("[FIELD]", renderer.render(new Field("FIELD")));
	}
	
	@Test
	public void withTableName() {
		assertEquals("[TABLE].[FIELD]", renderer.render(new Field("TABLE", "FIELD")));
	}

    @Test
    public void withTable() {
        assertEquals("[TABLE].[FIELD]", renderer.render(new Field(new Table("TABLE"), "FIELD")));
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
