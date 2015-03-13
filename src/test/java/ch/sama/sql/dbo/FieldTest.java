package ch.sama.sql.dbo;

import ch.sama.sql.query.base.IQueryFactory;
import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.tsql.dialect.TSqlQueryFactory;
import ch.sama.sql.tsql.type.IntType;
import ch.sama.sql.tsql.type.TYPE;
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
    
    @Test
    public void withType() {
        Field f = new Field("FIELD", TYPE.INT_TYPE);
        
        assertEquals("[FIELD]", f.getString(renderer));
        assertEquals(IntType.class, f.getDataType().getClass());
    }
    
    @Test
    public void withTableNameAndType() {
        Field f = new Field("TABLE", "FIELD", TYPE.INT_TYPE);

        assertEquals("[TABLE].[FIELD]", f.getString(renderer));
        assertEquals(IntType.class, f.getDataType().getClass());
    }

    @Test
    public void withTableAndType() {
        Field f = new Field(new Table("TABLE"), "FIELD", TYPE.INT_TYPE);

        assertEquals("[TABLE].[FIELD]", f.getString(renderer));
        assertEquals(IntType.class, f.getDataType().getClass());
    }
}
