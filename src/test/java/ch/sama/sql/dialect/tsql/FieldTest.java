package ch.sama.sql.dialect.tsql;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FieldTest {
    private static final TSqlQueryFactory sql = new TSqlQueryFactory();
    private static final TSqlQueryRenderer renderer = sql.renderer();

	@Test
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
