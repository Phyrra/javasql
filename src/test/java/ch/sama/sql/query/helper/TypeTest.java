package ch.sama.sql.query.helper;

import ch.sama.sql.query.helper.type.ITypeRenderer;
import ch.sama.sql.query.helper.type.TYPE;
import ch.sama.sql.tsql.dialect.TSqlTypeRenderer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TypeTest {
    private static final ITypeRenderer type = new TSqlTypeRenderer();
    
    @Test
    public void intType() {
        assertEquals("int", type.render(TYPE.INT_TYPE));
    }
    
    @Test
    public void floatType() {
        assertEquals("float", type.render(TYPE.FLOAT_TYPE));
    }
    
    @Test
    public void datetimeType() {
        assertEquals("datetime", type.render(TYPE.DATETIME_TYPE));
    }
    
    @Test
    public void varcharNoMax() {
        assertEquals("varchar", type.render(TYPE.VARCHAR_TYPE));
    }
    
    @Test
    public void varcharMax() {
        assertEquals("varchar(MAX)", type.render(TYPE.VARCHAR_MAX_TYPE));
    }
    
    @Test
    public void varcharIntMax() {
        assertEquals("varchar(64)", type.render(TYPE.VARCHAR_TYPE(64)));
    }
}
