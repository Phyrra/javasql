package ch.sama.sql.query.helper;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.IQueryFactory;
import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.base.ISourceFactory;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.tsql.dialect.TSqlQueryFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SourceTest {
    private static final IQueryFactory fac = new TSqlQueryFactory();
    private static final IQueryRenderer renderer = fac.renderer();
    private static final IValueFactory value = fac.value();
    private static final ISourceFactory source = fac.source();
    
    @Test
    public void stringTable() {
        assertEquals("[TABLE]", source.table("TABLE").getString(renderer));
    }
    
    @Test
    public void table() {
        assertEquals("[dbo].[TABLE]", source.table(new Table("dbo", "TABLE")).getString(renderer));
    }
    
    @Test
    public void tableWithSchema() {
        assertEquals("[dbo].[TABLE]", source.table("dbo", "TABLE").getString(renderer));
    }
    
    @Test
    public void withAlias() {
        assertEquals("[TABLE] AS [ALIAS]", source.table("TABLE").as("ALIAS").getString(renderer));
    }
    
    @Test
    public void aliasClone() {
        Source so = source.table("TABLE");
        Source sc = so.as("ALIAS");
        
        assertEquals(null, so.getAlias());
        assertEquals("ALIAS", sc.getAlias());
    }
    
    @Test (expected = IllegalIdentifierException.class)
    public void withBadAlias() {
        source.table("TABLE").as("'");
    }

    @Test
    public void query() {
        assertEquals("(\nSELECT 1\n) AS [QUERY]", source.query(fac.query().select(value.numeric(1))).as("QUERY").getString(renderer));
    }
}
