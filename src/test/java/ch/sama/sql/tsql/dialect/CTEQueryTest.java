package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.base.IQueryFactory;
import ch.sama.sql.query.base.ISourceFactory;
import ch.sama.sql.query.base.IValueFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class CTEQueryTest {
    private static final IQueryFactory fac = new TSqlQueryFactory();
    private static final IValueFactory value = fac.value();
    private static final ISourceFactory source = fac.source();
	
	@Test
	public void cte() {
		assertEquals(
                "WITH [CTE] AS (\nSELECT [F]\nFROM [T]\n)",
                fac.query()
                        .with("CTE").as(
                                fac.query()
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
                fac.query()
                        .with("CTE").as(
                                fac.query()
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
                fac.query()
                        .with("CTE1").as(
                                fac.query()
                                        .select(value.field("F"))
                                        .from(source.table("T"))
                        )
                        .with("CTE2").as(
                                fac.query()
                                        .select(value.field("F"))
                                        .from(source.table("T"))
                        )
                        .select(value.field("F"))
                        .from(source.table("CTE1"))
                .getSql()
        );
    }
}
