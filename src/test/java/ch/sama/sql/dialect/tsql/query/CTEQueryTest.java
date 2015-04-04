package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.dialect.tsql.TSqlQueryBuilder;
import ch.sama.sql.dialect.tsql.TSqlSourceFactory;
import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CTEQueryTest {
    private static final TSqlQueryBuilder sql = new TSqlQueryBuilder();
    private static final TSqlValueFactory value = sql.value();
    private static final TSqlSourceFactory source = sql.source();
	
	@Test
	public void cte() {
		assertEquals(
                "WITH [CTE] AS (\nSELECT [F]\nFROM [T]\n)",
                sql.query()
                        .with("CTE")
                        .as(
                                sql.query()
                                        .select(value.field("F"))
                                        .from(source.table("T"))
                        )
                .getSql()
        );
	}
	
	@Test
	public void selectCte() {
		assertEquals(
                "WITH [CTE] AS (\nSELECT [F]\nFROM [T]\n)\nSELECT [F]\nFROM [CTE]",
                sql.query()
                        .with("CTE")
                        .as(
                                sql.query()
                                        .select(value.field("F"))
                                        .from(source.table("T"))
                        )
                        .select(value.field("F"))
                        .from(source.table("CTE"))
                .getSql()
        );
	}

    @Test
    public void multiCte() {
        assertEquals(
                "WITH [CTE1] AS (\nSELECT [F]\nFROM [T]\n), [CTE2] AS (\nSELECT [F]\nFROM [T]\n)\nSELECT [F]\nFROM [CTE1]",
                sql.query()
                        .with("CTE1")
                        .as(
                                sql.query()
                                        .select(value.field("F"))
                                        .from(source.table("T"))
                        )
                        .with("CTE2")
                        .as(
                                sql.query()
                                        .select(value.field("F"))
                                        .from(source.table("T"))
                        )
                        .select(value.field("F"))
                        .from(source.table("CTE1"))
                .getSql()
        );
    }
    
    @Test (expected = IllegalIdentifierException.class)
    public void badName() {
        sql.query()
                .with("'")
                .as(
                        sql.query()
                                .select(value.field("F"))
                                .from(source.table("T"))
                );
    }
    
    @Test (expected = BadSqlException.class)
    public void nestedCte() {
        sql.query()
                .with("CTE1")
                .as(
                        sql.query()
                                .with("CTE2").as(
                                sql.query()
                                        .select(value.field("F"))
                                        .from(source.table("T"))
                        )
                );
    }
}