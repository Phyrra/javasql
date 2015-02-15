package ch.sama.sql.tsql.dialect;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.query.base.IValueFactory;
import ch.sama.sql.query.exception.BadParameterException;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class MergeTest {
    private static final TSqlMerger m = new TSqlMerger();
    private static final IValueFactory value = new TSqlQueryFactory().value();
    
    @Test
    public void oneRowOneValueOneMatchNoOmit() {
        Field f = new Field("FIELD");
        f.setDataType("int");
        
        assertEquals(
                "DECLARE @table (\nFIELD int\n)\nINSERT INTO @table\nSELECT 1\nMERGE INTO [TABLE] AS [old]\nUSING @table AS [new] ON (\n[old].[FIELD] = [new].[FIELD]\n)\nWHEN MATCHED THEN\nUPDATE SET [FIELD] = [new].[FIELD]\nWHEN NOT MATCHED BY TARGET THEN\nINSERT ([FIELD]) VALUES ([new].[FIELD])\nOUTPUT INSERTED.[FIELD]",
                m.merge("TABLE")
                        .values(
                                m.row(m.value(f, value.numeric(1)))
                        )
                        .matching(f)
                        .omit()
                .getSql()
        );
    }

    @Test
    public void oneRowMultiValueOneMatchNoOmit() {
        Field f1 = new Field("FIELD1");
        f1.setDataType("int");

        Field f2 = new Field("FIELD2");
        f2.setDataType("int");
        
        assertEquals(
                "DECLARE @table (\nFIELD1 int,\nFIELD2 int\n)\nINSERT INTO @table\nSELECT 1, 2\nMERGE INTO [TABLE] AS [old]\nUSING @table AS [new] ON (\n[old].[FIELD1] = [new].[FIELD1]\n)\nWHEN MATCHED THEN\nUPDATE SET [FIELD1] = [new].[FIELD1], [FIELD2] = [new].[FIELD2]\nWHEN NOT MATCHED BY TARGET THEN\nINSERT ([FIELD1], [FIELD2]) VALUES ([new].[FIELD1], [new].[FIELD2])\nOUTPUT INSERTED.[FIELD1], INSERTED.[FIELD2]",
                m.merge("TABLE")
                        .values(
                                m.row(m.value(f1, value.numeric(1)), m.value(f2, value.numeric(2)))
                        )
                        .matching(f1)
                        .omit()
                .getSql()
        );
    }

    @Test
    public void oneRowMultiValueMultiMatchNoOmit() {
        Field f1 = new Field("FIELD1");
        f1.setDataType("int");

        Field f2 = new Field("FIELD2");
        f2.setDataType("int");

        assertEquals(
                "DECLARE @table (\nFIELD1 int,\nFIELD2 int\n)\nINSERT INTO @table\nSELECT 1, 2\nMERGE INTO [TABLE] AS [old]\nUSING @table AS [new] ON (\n[old].[FIELD1] = [new].[FIELD1] AND [old].[FIELD2] = [new].[FIELD2]\n)\nWHEN MATCHED THEN\nUPDATE SET [FIELD1] = [new].[FIELD1], [FIELD2] = [new].[FIELD2]\nWHEN NOT MATCHED BY TARGET THEN\nINSERT ([FIELD1], [FIELD2]) VALUES ([new].[FIELD1], [new].[FIELD2])\nOUTPUT INSERTED.[FIELD1], INSERTED.[FIELD2]",
                m.merge("TABLE")
                        .values(
                                m.row(m.value(f1, value.numeric(1)), m.value(f2, value.numeric(2)))
                        )
                        .matching(f1, f2)
                        .omit()
                .getSql()
        );
    }

    @Test
    public void oneRowMultiValueOneMatchOneOmit() {
        Field f1 = new Field("FIELD1");
        f1.setDataType("int");

        Field f2 = new Field("FIELD2");
        f2.setDataType("int");

        assertEquals(
                "DECLARE @table (\nFIELD1 int,\nFIELD2 int\n)\nINSERT INTO @table\nSELECT 1, 2\nMERGE INTO [TABLE] AS [old]\nUSING @table AS [new] ON (\n[old].[FIELD1] = [new].[FIELD1]\n)\nWHEN MATCHED THEN\nUPDATE SET [FIELD2] = [new].[FIELD2]\nWHEN NOT MATCHED BY TARGET THEN\nINSERT ([FIELD2]) VALUES ([new].[FIELD2])\nOUTPUT INSERTED.[FIELD1], INSERTED.[FIELD2]",
                m.merge("TABLE")
                        .values(
                                m.row(m.value(f1, value.numeric(1)), m.value(f2, value.numeric(2)))
                        )
                        .matching(f1)
                        .omit(f1)
                .getSql()
        );
    }

    @Test
    public void oneRowMultiValueOneMatchMultiOmit() {
        Field f1 = new Field("FIELD1");
        f1.setDataType("int");

        Field f2 = new Field("FIELD2");
        f2.setDataType("int");

        Field f3 = new Field("FIELD3");
        f3.setDataType("int");

        assertEquals(
                "DECLARE @table (\nFIELD1 int,\nFIELD2 int,\nFIELD3 int\n)\nINSERT INTO @table\nSELECT 1, 2, 3\nMERGE INTO [TABLE] AS [old]\nUSING @table AS [new] ON (\n[old].[FIELD1] = [new].[FIELD1]\n)\nWHEN MATCHED THEN\nUPDATE SET [FIELD3] = [new].[FIELD3]\nWHEN NOT MATCHED BY TARGET THEN\nINSERT ([FIELD3]) VALUES ([new].[FIELD3])\nOUTPUT INSERTED.[FIELD1], INSERTED.[FIELD2], INSERTED.[FIELD3]",
                m.merge("TABLE")
                        .values(
                                m.row(m.value(f1, value.numeric(1)), m.value(f2, value.numeric(2)), m.value(f3, value.numeric(3)))
                        )
                        .matching(f1)
                        .omit(f1, f2)
                .getSql()
        );
    }

    @Test
    public void multiRowOneValueOneMatchNoOmit() {
        Field f = new Field("FIELD");
        f.setDataType("int");

        assertEquals(
                "DECLARE @table (\nFIELD int\n)\nINSERT INTO @table\nSELECT 1 UNION ALL\nSELECT 2\nMERGE INTO [TABLE] AS [old]\nUSING @table AS [new] ON (\n[old].[FIELD] = [new].[FIELD]\n)\nWHEN MATCHED THEN\nUPDATE SET [FIELD] = [new].[FIELD]\nWHEN NOT MATCHED BY TARGET THEN\nINSERT ([FIELD]) VALUES ([new].[FIELD])\nOUTPUT INSERTED.[FIELD]",
                m.merge("TABLE")
                        .values(
                                m.row(m.value(f, value.numeric(1))),
                                m.row(m.value(f, value.numeric(2)))
                        )
                        .matching(f)
                        .omit()
                .getSql()
        );
    }
    
    @Test (expected = BadParameterException.class)
    public void noValues() {
        m.merge("TABLE").values();
    }
    
    @Test (expected = BadParameterException.class)
    public void noType() {
        m.merge("TABLE").values(m.row(m.value(new Field("FIELD"), value.numeric(1))));
    }
    
    @Test (expected = BadParameterException.class)
    public void unevenValues() {
        Field f1 = new Field("FIELD1");
        f1.setDataType("int");

        Field f2 = new Field("FIELD2");
        f2.setDataType("int");
        
        m.merge("TABLE")
                .values(
                        m.row(m.value(f1, value.numeric(1))),
                        m.row(m.value(f1, value.numeric(1)), m.value(f2, value.numeric(2)))
                );
    }
    
    @Test (expected = BadParameterException.class)
    public void omitAll() {
        Field f1 = new Field("FIELD1");
        f1.setDataType("int");

        m.merge("TABLE")
                .values(
                        m.row(m.value(f1, value.numeric(1)))
                )
                .matching(f1)
                .omit(f1);
    }

    @Test
    public void guessNull() {
        assertEquals("none", m.value("FIELD", null).getField().getDataType());
    }
    
    @Test
    public void guessDouble() {
        assertEquals("float", m.value("FIELD", 1.23).getField().getDataType());
    }

    @Test
    public void guessFloat() {
        assertEquals("float", m.value("FIELD", 1.23f).getField().getDataType());
    }

    @Test
    public void guessShort() {
        assertEquals("int", m.value("FIELD", (short)1).getField().getDataType());
    }

    @Test
    public void guessInt() {
        assertEquals("int", m.value("FIELD", 1).getField().getDataType());
    }

    @Test
    public void guessLong() {
        assertEquals("int", m.value("FIELD", (long)1).getField().getDataType());
    }

    @Test
    public void guessDate() {
        assertEquals("datetime", m.value("FIELD", new Date()).getField().getDataType());
    }

    @Test
    public void guessNullString() {
        assertEquals("int", m.value("FIELD", "NuLl").getField().getDataType());
    }

    @Test
    public void guessIntString() {
        assertEquals("float", m.value("FIELD", "1").getField().getDataType());
    }

    @Test
    public void guessFloatString() {
        assertEquals("float", m.value("FIELD", "1.23").getField().getDataType());
    }

    @Test
    public void guessNormDateString() {
        assertEquals("datetime", m.value("FIELD", "14.02.2015").getField().getDataType());
    }

    @Test
    public void guessIsoDateString() {
        assertEquals("datetime", m.value("FIELD", "2015-02-14").getField().getDataType());
    }

    @Test
    public void guessString() {
        assertEquals("varchar(MAX)", m.value("FIELD", "Hello").getField().getDataType());
    }

    @Test (expected = BadParameterException.class)
    public void wrongGuess() {
        m.value("FIELD", new Object());
    }

    @Test
    public void fillGuessHoles() {
        assertEquals(
                "DECLARE @table (\nFIELD1 float,\nFIELD2 int,\nFIELD3 varchar(MAX)\n)\nINSERT INTO @table\nSELECT NULL, NULL, 'Hello' UNION ALL\nSELECT 1.23, NULL, NULL\nMERGE INTO [TABLE] AS [old]\nUSING @table AS [new] ON (\n[old].[FIELD1] = [new].[FIELD1]\n)\nWHEN MATCHED THEN\nUPDATE SET [FIELD1] = [new].[FIELD1], [FIELD2] = [new].[FIELD2], [FIELD3] = [new].[FIELD3]\nWHEN NOT MATCHED BY TARGET THEN\nINSERT ([FIELD1], [FIELD2], [FIELD3]) VALUES ([new].[FIELD1], [new].[FIELD2], [new].[FIELD3])\nOUTPUT INSERTED.[FIELD1], INSERTED.[FIELD2], INSERTED.[FIELD3]",
                m.merge("TABLE")
                        .values(
                                m.row(m.value("FIELD1", null), m.value("FIELD2", null), m.value("FIELD3", "Hello")),
                                m.row(m.value("FIELD1", 1.23), m.value("FIELD2", null), m.value("FIELD3", null))
                        )
                        .matching("FIELD1")
                        .omit()
                .getSql()
        );
    }
}
