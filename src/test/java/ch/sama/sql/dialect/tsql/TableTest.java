package ch.sama.sql.dialect.tsql;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.dialect.tsql.TSqlQueryFactory;
import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TableTest {
    private static final TSqlQueryFactory sql = new TSqlQueryFactory();
    private static final TSqlQueryRenderer renderer = sql.renderer();

    @Test
	public void nameOnly() {
		assertEquals("[NAME]", renderer.render(new Table("NAME")));
	}
	
	@Test
	public void withSchema() {
		assertEquals("[dbo].[NAME]", renderer.render(new Table("dbo", "NAME")));
	}
	
	@Test (expected = IllegalIdentifierException.class)
	public void badTable() {
		new Table("'");
	}
	
	@Test (expected =  IllegalIdentifierException.class)
	public void badSchema() {
		new Table("'", "TABLE");
	}
	
	@Test
	public void getColumns() {
		Table t = new Table("TABLE");
		
		t.addColumn(new Field("COLUMN1"));
		t.addColumn(new Field("COLUMN2"));
		
		assertEquals(2, t.getColumns().size());
	}
	
	@Test
	public void getColumn() {
		Table t = new Table("TABLE");
		
		t.addColumn(new Field("COLUMN1"));
		
		assertEquals(true, t.hasColumn("COLUMN1"));
		assertEquals("COLUMN1", t.getColumn("COLUMN1").getName());
	}

    @Test
    public void getSinglePrimaryKey() {
        Table t = new Table("TABLE");

        Field key = new Field("COLUMN");
        key.setAsPrimaryKey();

        t.addColumn(key);

        List<Field> pKey = t.getPrimaryKey();

        assertEquals(1, pKey.size());
        assertEquals("COLUMN", pKey.get(0).getName());
    }

    @Test
    public void getMultiplePrimaryKey() {
        Table t = new Table("TABLE");

        Field key1 = new Field("COLUMN1");
        key1.setAsPrimaryKey();
        t.addColumn(key1);

        Field key2 = new Field("COLUMN2");
        key2.setAsPrimaryKey();
        t.addColumn(key2);

        assertEquals(2, t.getPrimaryKey().size());
    }
}
