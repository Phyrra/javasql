package ch.sama.sql.tsql.dialect;

import static org.junit.Assert.*;

import ch.sama.sql.query.base.QueryFactory;
import org.junit.Test;

public class CTEQueryTest {
    private static final QueryFactory fac = new TSqlQueryFactory();
	
	@Test(expected = NullPointerException.class)
	public void nullPointer() {
		fac.create()
                .with("CTE")
        .toString();
	}
	
	@Test
	public void cte() {
		assertEquals(
			"WITH CTE AS (\nSELECT [F]\nFROM [T]\n)",
			fac.create()
				.with("CTE").as(
					fac.create()
                            .select(fac.field("F"))
                            .from(fac.table("T"))
				)
			.toString()
		);
	}
	
	@Test
	public void selectCte() {
		assertEquals(
			"WITH CTE AS (\nSELECT [F]\nFROM [T]\n)\nSELECT [F]\nFROM [CTE]",
			fac.create()
				.with("CTE").as(
					fac.create()
                            .select(fac.field("F"))
                            .from(fac.table("T"))
				)
				.select(fac.field("F"))
                .from(fac.table("CTE"))
            .toString()
		);
	}

    @Test
    public void multiCte() {
        assertEquals(
            "WITH CTE1 AS (\nSELECT [F]\nFROM [T]\n), CTE2 AS (\nSELECT [F]\nFROM [T]\n)\nSELECT [F]\nFROM [CTE1]",
            fac.create()
                .with("CTE1").as(
                    fac.create()
                            .select(fac.field("F"))
                            .from(fac.table("T"))
                )
                .with("CTE2").as(
                    fac.create()
                            .select(fac.field("F"))
                            .from(fac.table("T"))
                )
                .select(fac.field("F"))
                .from(fac.table("CTE1"))
            .toString()
        );
    }
}
