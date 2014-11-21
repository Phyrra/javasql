package ch.sama.sql.dbo;

import static org.junit.Assert.*;

import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.tsql.dialect.TSqlQueryRenderer;
import org.junit.Test;

public class FieldTest {
    private static final IQueryRenderer renderer = new TSqlQueryRenderer();

	@Test
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
}
