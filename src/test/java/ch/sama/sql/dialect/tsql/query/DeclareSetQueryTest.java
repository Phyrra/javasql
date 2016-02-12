package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dialect.tsql.TSqlQueryFactory;
import ch.sama.sql.dialect.tsql.TSqlSourceFactory;
import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import ch.sama.sql.dialect.tsql.type.TYPE;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.query.standard.ValueFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeclareSetQueryTest {
    private static final TSqlQueryFactory sql = new TSqlQueryFactory();
    private static final TSqlValueFactory value = sql.value();
    private static final TSqlSourceFactory source = sql.source();

    @Test (expected = IllegalIdentifierException.class)
    public void badVariable() {
        sql.query()
                .declare("@VAR", TYPE.INT_TYPE, ValueFactory.NULL);
    }

    @Test
    public void selectSet() {
        assertEquals(
                "DECLARE @VAR AS int\nSET @VAR = 1;",
                sql.query()
                        .declare("VAR", TYPE.INT_TYPE, value.numeric(1))
                        .getSql()
        );
    }

    @Test
    public void setAsQuery() {
        assertEquals(
                "DECLARE @VAR AS int\nSET @VAR = (\nSELECT TOP 1 [ID]\nFROM [TABLE]\n);",
                sql.query()
                        .declare(
                                "VAR",
                                TYPE.INT_TYPE,
                                value.query(
                                        sql.query()
                                                .select(value.field("ID")).top(1)
                                                .from(source.table("TABLE"))
                                )
                        )
                .getSql()
        );
    }

    @Test
    public void multipleDeclares() {
        assertEquals(
                "DECLARE @VAR1 AS int\nSET @VAR1 = 1;\nDECLARE @VAR2 AS int\nSET @VAR2 = 2;",
                sql.query()
                        .declare("VAR1", TYPE.INT_TYPE, value.numeric(1))
                        .declare("VAR2", TYPE.INT_TYPE, value.numeric(2))
                .getSql()
        );
    }

    @Test
    public void selectDeclared() {
        assertEquals(
                "DECLARE @VAR AS int\nSET @VAR = 1;\nSELECT @VAR AS [I]",
                sql.query()
                        .declare("VAR", TYPE.INT_TYPE, value.numeric(1))
                        .select(value.variable("VAR").as("I"))
                .getSql()
        );
    }

    @Test
    public void cteAfterDeclare() {
        assertEquals(
                "DECLARE @VAR AS int\nSET @VAR = 1;\nWITH [cte] AS (\nSELECT @VAR AS [ID]\n)\nSELECT [ID]\nFROM [cte]",
                sql.query()
                        .declare("VAR", TYPE.INT_TYPE, value.numeric(1))
                        .with("cte")
                        .as(
                                sql.query()
                                        .select(value.variable("VAR").as("ID"))
                        )
                        .select(value.field("ID"))
                        .from(source.table("cte"))
                .getSql()
        );
    }

    @Test
    public void cteAfterCteAfterDeclare() {
        assertEquals(
                "DECLARE @VAR AS int\nSET @VAR = 1;\nWITH [cte1] AS (\nSELECT @VAR AS [ID]\n), [cte2] AS (\nSELECT @VAR AS [ID]\n)\nSELECT [ID]\nFROM [cte1]\nUNION ALL\nSELECT [ID]\nFROM [cte2]",
                sql.query()
                        .declare("VAR", TYPE.INT_TYPE, value.numeric(1))
                        .with("cte1")
                        .as(
                                sql.query()
                                        .select(value.variable("VAR").as("ID"))
                        )
                        .with("cte2")
                        .as(
                                sql.query()
                                        .select(value.variable("VAR").as("ID"))
                        )
                        .union(
                                sql.query()
                                        .select(value.field("ID"))
                                        .from(source.table("cte1")),
                                sql.query()
                                        .select(value.field("ID"))
                                        .from(source.table("cte2"))
                        )
                .getSql()
        );
    }
}
