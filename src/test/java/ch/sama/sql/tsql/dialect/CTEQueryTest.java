package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.base.ISourceFactory;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.base.Query;
import org.junit.Test;

import static org.junit.Assert.*;

public class CTEQueryTest {
    private static final IValueFactory value = new TSqlValueFactory();
    private static final ISourceFactory source = new TSqlSourceFactory();
    private static final IQueryRenderer renderer = new TSqlQueryRenderer();
	
	@Test
	public void cte() {
		assertEquals(
			"WITH CTE AS (\nSELECT [F]\nFROM [T]\n)",
			new Query()
				.with("CTE").as(
                    new Query()
                            .select(value.field("F"))
                            .from(source.table("T"))
            )
			.getSql(renderer)
		);
	}
	
	@Test
	public void selectCte() {
		assertEquals(
			"WITH CTE AS (\nSELECT [F]\nFROM [T]\n)\nSELECT [F]\nFROM [CTE]",
			new Query()
				.with("CTE").as(
					new Query()
                            .select(value.field("F"))
                            .from(source.table("T"))
				)
				.select(value.field("F"))
                .from(source.table("CTE"))
            .getSql(renderer)
		);
	}

    @Test
    public void multiCte() {
        assertEquals(
            "WITH CTE1 AS (\nSELECT [F]\nFROM [T]\n), CTE2 AS (\nSELECT [F]\nFROM [T]\n)\nSELECT [F]\nFROM [CTE1]",
            new Query()
                .with("CTE1").as(
                    new Query()
                            .select(value.field("F"))
                            .from(source.table("T"))
                )
                .with("CTE2").as(
                    new Query()
                            .select(value.field("F"))
                            .from(source.table("T"))
                )
                .select(value.field("F"))
                .from(source.table("CTE1"))
            .getSql(renderer)
        );
    }
}
