package ch.sama.sql.tsql.dialect;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.Query;

public class CTEQueryTest {
	private static final Query query = new TSqlQuery();
	
	@Test(expected = NullPointerException.class)
	public void nullPointer() {
		query.with("CTE").toString();
	}
	
	@Test
	public void cte() {
		assertEquals(
			"WITH CTE AS (\nSELECT [F]\nFROM [T]\n)",
			query
				.with("CTE").as(
					new TSqlQuery().select(new TSqlValue(new Field("F"))).from(new Table("T"))
				)
			.toString()
		);
	}
	
	@Test
	public void selectCte() {
		assertEquals(
			"WITH CTE AS (\nSELECT [F]\nFROM [T]\n)\nSELECT [F]\nFROM [CTE]",
			query
				.with("CTE").as(
					new TSqlQuery().select(new TSqlValue(new Field("F"))).from(new Table("T"))
				)
				.select(new TSqlValue(new Field("F"))).from(new Table("CTE"))
            .toString()
		);
	}

    @Test
    public void multiCte() {
        assertEquals(
            "WITH CTE1 AS (\nSELECT [F]\nFROM [T]\n), CTE2 AS (\nSELECT [F]\nFROM [T]\n)\nSELECT [F]\nFROM [CTE1]",
            query
                .with("CTE1").as(
                    new TSqlQuery().select(new TSqlValue(new Field("F"))).from(new Table("T"))
                )
                .with("CTE2").as(
                    new TSqlQuery().select(new TSqlValue(new Field("F"))).from(new Table("T"))
                )
                .select(new TSqlValue(new Field("F"))).from(new Table("CTE1"))
            .toString()
        );
    }
}
