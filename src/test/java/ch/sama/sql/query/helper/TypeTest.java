package ch.sama.sql.query.helper;

import ch.sama.sql.tsql.type.TYPE;
import ch.sama.sql.tsql.dialect.TSqlQueryFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TypeTest {
    @Test
    public void uniqueidentifier() {
        assertEquals("uniqueidentifier", TYPE.UNIQUEIDENTIFIER_TYPE.getString());    
    }
    
    @Test
    public void intType() {
        assertEquals("int", TYPE.INT_TYPE.getString());
    }
    
    @Test
    public void floatType() {
        assertEquals("float", TYPE.FLOAT_TYPE.getString());
    }
    
    @Test
    public void datetimeType() {
        assertEquals("datetime", TYPE.DATETIME_TYPE.getString());
    }
    
    @Test
    public void varcharNoMax() {
        assertEquals("varchar", TYPE.VARCHAR_TYPE.getString());
    }
    
    @Test
    public void varcharMax() {
        assertEquals("varchar(MAX)", TYPE.VARCHAR_MAX_TYPE.getString());
    }
    
    @Test
    public void varcharIntMax() {
        assertEquals("varchar(64)", TYPE.VARCHAR_TYPE(64).getString());
    }

    @Test
    public void nvarcharNoMax() {
        assertEquals("nvarchar", TYPE.NVARCHAR_TYPE.getString());
    }

    @Test
    public void nvarcharMax() {
        assertEquals("nvarchar(MAX)", TYPE.NVARCHAR_MAX_TYPE.getString());
    }

    @Test
    public void nvarcharIntMax() {
        assertEquals("nvarchar(64)", TYPE.NVARCHAR_TYPE(64).getString());
    }
}
