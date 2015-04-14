package ch.sama.sql.query.helper;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.dialect.tsql.TSqlQueryFactory;
import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.dialect.tsql.TSqlSourceFactory;
import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SourceTest {
    private static final TSqlQueryFactory sql = new TSqlQueryFactory();
    private static final TSqlQueryRenderer renderer = sql.renderer();
    private static final TSqlValueFactory value = sql.value();
    private static final TSqlSourceFactory source = sql.source();
    
    @Test
    public void stringTable() {
        assertEquals("[TABLE]", renderer.render(source.table("TABLE")));
    }
    
    @Test
    public void table() {
        assertEquals("[dbo].[TABLE]", renderer.render(source.table(new Table("dbo", "TABLE"))));
    }
    
    @Test
    public void tableWithSchema() {
        assertEquals("[dbo].[TABLE]", renderer.render(source.table("dbo", "TABLE")));
    }
    
    @Test
    public void withAlias() {
        assertEquals("[TABLE] AS [ALIAS]", renderer.render(source.table("TABLE").as("ALIAS")));
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
        assertEquals("(\nSELECT 1\n) AS [QUERY]", renderer.render(source.query(sql.query().select(value.numeric(1))).as("QUERY")));
    }
}
