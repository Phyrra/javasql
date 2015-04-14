package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.csv.CSVRow;
import ch.sama.sql.csv.CSVSet;
import ch.sama.sql.dbo.Field;
import ch.sama.sql.query.exception.BadParameterException;
import ch.sama.sql.dialect.tsql.TSqlMerger;
import ch.sama.sql.dialect.tsql.TSqlQueryFactory;
import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import ch.sama.sql.dialect.tsql.type.*;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class MergeTest {
    private static final TSqlMerger m = new TSqlMerger();
    private static final TSqlValueFactory value = new TSqlQueryFactory().value();
    
    @Test
    public void oneRowOneValueOneMatchNoOmit() {
        Field f = new Field("FIELD");
        f.setDataType(TYPE.INT_TYPE);
        
        assertEquals(
                "DECLARE @table TABLE (\nFIELD int\n);\nINSERT INTO @table\nSELECT 1;\nMERGE INTO [TABLE] AS [old]\nUSING @table AS [new] ON (\n[old].[FIELD] = [new].[FIELD]\n)\nWHEN MATCHED THEN\nUPDATE SET [FIELD] = [new].[FIELD]\nWHEN NOT MATCHED BY TARGET THEN\nINSERT ([FIELD]) VALUES ([new].[FIELD])\nOUTPUT INSERTED.[FIELD];",
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
        f1.setDataType(TYPE.INT_TYPE);

        Field f2 = new Field("FIELD2");
        f2.setDataType(TYPE.INT_TYPE);
        
        assertEquals(
                "DECLARE @table TABLE (\nFIELD1 int,\nFIELD2 int\n);\nINSERT INTO @table\nSELECT 1, 2;\nMERGE INTO [TABLE] AS [old]\nUSING @table AS [new] ON (\n[old].[FIELD1] = [new].[FIELD1]\n)\nWHEN MATCHED THEN\nUPDATE SET [FIELD1] = [new].[FIELD1], [FIELD2] = [new].[FIELD2]\nWHEN NOT MATCHED BY TARGET THEN\nINSERT ([FIELD1], [FIELD2]) VALUES ([new].[FIELD1], [new].[FIELD2])\nOUTPUT INSERTED.[FIELD1], INSERTED.[FIELD2];",
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
        f1.setDataType(TYPE.INT_TYPE);

        Field f2 = new Field("FIELD2");
        f2.setDataType(TYPE.INT_TYPE);

        assertEquals(
                "DECLARE @table TABLE (\nFIELD1 int,\nFIELD2 int\n);\nINSERT INTO @table\nSELECT 1, 2;\nMERGE INTO [TABLE] AS [old]\nUSING @table AS [new] ON (\n[old].[FIELD1] = [new].[FIELD1] AND [old].[FIELD2] = [new].[FIELD2]\n)\nWHEN MATCHED THEN\nUPDATE SET [FIELD1] = [new].[FIELD1], [FIELD2] = [new].[FIELD2]\nWHEN NOT MATCHED BY TARGET THEN\nINSERT ([FIELD1], [FIELD2]) VALUES ([new].[FIELD1], [new].[FIELD2])\nOUTPUT INSERTED.[FIELD1], INSERTED.[FIELD2];",
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
        f1.setDataType(TYPE.INT_TYPE);

        Field f2 = new Field("FIELD2");
        f2.setDataType(TYPE.INT_TYPE);

        assertEquals(
                "DECLARE @table TABLE (\nFIELD1 int,\nFIELD2 int\n);\nINSERT INTO @table\nSELECT 1, 2;\nMERGE INTO [TABLE] AS [old]\nUSING @table AS [new] ON (\n[old].[FIELD1] = [new].[FIELD1]\n)\nWHEN MATCHED THEN\nUPDATE SET [FIELD2] = [new].[FIELD2]\nWHEN NOT MATCHED BY TARGET THEN\nINSERT ([FIELD2]) VALUES ([new].[FIELD2])\nOUTPUT INSERTED.[FIELD1], INSERTED.[FIELD2];",
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
        f1.setDataType(TYPE.INT_TYPE);

        Field f2 = new Field("FIELD2");
        f2.setDataType(TYPE.INT_TYPE);

        Field f3 = new Field("FIELD3");
        f3.setDataType(TYPE.INT_TYPE);

        assertEquals(
                "DECLARE @table TABLE (\nFIELD1 int,\nFIELD2 int,\nFIELD3 int\n);\nINSERT INTO @table\nSELECT 1, 2, 3;\nMERGE INTO [TABLE] AS [old]\nUSING @table AS [new] ON (\n[old].[FIELD1] = [new].[FIELD1]\n)\nWHEN MATCHED THEN\nUPDATE SET [FIELD3] = [new].[FIELD3]\nWHEN NOT MATCHED BY TARGET THEN\nINSERT ([FIELD3]) VALUES ([new].[FIELD3])\nOUTPUT INSERTED.[FIELD1], INSERTED.[FIELD2], INSERTED.[FIELD3];",
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
        f.setDataType(TYPE.INT_TYPE);

        assertEquals(
                "DECLARE @table TABLE (\nFIELD int\n);\nINSERT INTO @table\nSELECT 1 UNION ALL\nSELECT 2;\nMERGE INTO [TABLE] AS [old]\nUSING @table AS [new] ON (\n[old].[FIELD] = [new].[FIELD]\n)\nWHEN MATCHED THEN\nUPDATE SET [FIELD] = [new].[FIELD]\nWHEN NOT MATCHED BY TARGET THEN\nINSERT ([FIELD]) VALUES ([new].[FIELD])\nOUTPUT INSERTED.[FIELD];",
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
        f1.setDataType(TYPE.INT_TYPE);

        Field f2 = new Field("FIELD2");
        f2.setDataType(TYPE.INT_TYPE);

        m.merge("TABLE")
                .values(
                        m.row(m.value(f1, value.numeric(1))),
                        m.row(m.value(f1, value.numeric(1)), m.value(f2, value.numeric(2)))
                );
    }
    
    @Test (expected = BadParameterException.class)
    public void omitAll() {
        Field f1 = new Field("FIELD1");
        f1.setDataType(TYPE.INT_TYPE);

        m.merge("TABLE")
                .values(
                        m.row(m.value(f1, value.numeric(1)))
                )
                .matching(f1)
                .omit(f1);
    }

    @Test
    public void guessNull() {
        assertEquals("none", m.value("FIELD", null).getField().getDataType().getString());
    }

    @Test
    public void guessBit() {
        assertEquals(BitType.class, m.value("FIELD", true).getField().getDataType().getClass());
    }
    
    @Test
    public void guessShort() {
        assertEquals(IntType.class, m.value("FIELD", (short)1).getField().getDataType().getClass());
    }

    @Test
    public void guessInt() {
        assertEquals(IntType.class, m.value("FIELD", 1).getField().getDataType().getClass());
    }

    @Test
    public void guessLong() {
        assertEquals(IntType.class, m.value("FIELD", (long)1).getField().getDataType().getClass());
    }

    @Test
    public void guessFloat() {
        assertEquals(FloatType.class, m.value("FIELD", 1.23f).getField().getDataType().getClass());
    }
    
    @Test
    public void guessDouble() {
        assertEquals(FloatType.class, m.value("FIELD", 1.23).getField().getDataType().getClass());
    }

    @Test
    public void guessDate() {
        assertEquals(DatetimeType.class, m.value("FIELD", new Date()).getField().getDataType().getClass());
    }

    @Test
    public void guessNullString() {
        assertEquals("none", m.value("FIELD", "NuLl").getField().getDataType().getString());
    }

    @Test
    public void guessIntString() {
        assertEquals(IntType.class, m.value("FIELD", "1").getField().getDataType().getClass());
    }

    @Test
    public void guessFloatString() {
        assertEquals(FloatType.class, m.value("FIELD", "1.23").getField().getDataType().getClass());
    }

    @Test
    public void guessNormDateString() {
        TSqlMerger.Pair p = m.value("FIELD", "14.02.2015");

        assertEquals(DatetimeType.class, p.getField().getDataType().getClass());
        assertEquals("CONVERT(datetime, '2015-02-14 00:00:00', 21)", p.getValue().getValue());
    }

    @Test
    public void guessNormDateStringIncomplete() {
        TSqlMerger.Pair p = m.value("FIELD", "8.4.2015");

        assertEquals(DatetimeType.class, p.getField().getDataType().getClass());
        assertEquals("CONVERT(datetime, '2015-04-08 00:00:00', 21)", p.getValue().getValue());
    }

    @Test
    public void guessNormDateTimeString() {
        TSqlMerger.Pair p = m.value("FIELD", "08.04.2015 16:32:00");

        assertEquals(DatetimeType.class, p.getField().getDataType().getClass());
        assertEquals("CONVERT(datetime, '2015-04-08 16:32:00', 21)", p.getValue().getValue());
    }

    @Test
    public void guessNormDateTimeStringIncomplete() {
        TSqlMerger.Pair p = m.value("FIELD", "8.4.2015 16:40:00");

        assertEquals(DatetimeType.class, p.getField().getDataType().getClass());
        assertEquals("CONVERT(datetime, '2015-04-08 16:40:00', 21)", p.getValue().getValue());
    }

    @Test
    public void guessIsoDateString() {
        TSqlMerger.Pair p = m.value("FIELD", "2015-02-14");

        assertEquals(DatetimeType.class, p.getField().getDataType().getClass());
        assertEquals("CONVERT(datetime, '2015-02-14 00:00:00', 21)", p.getValue().getValue());
    }

    @Test
    public void guessIsoDateTimeString() {
        TSqlMerger.Pair p = m.value("FIELD", "2015-02-14 16:38:00");

        assertEquals(DatetimeType.class, p.getField().getDataType().getClass());
        assertEquals("CONVERT(datetime, '2015-02-14 16:38:00', 21)", p.getValue().getValue());
    }

    @Test
    public void guessString() {
        assertEquals("varchar(MAX)", m.value("FIELD", "Hello").getField().getDataType().getString());
    }

    @Test (expected = BadParameterException.class)
    public void wrongGuess() {
        m.value("FIELD", new Object());
    }

    @Test
    public void fillGuessHoles() {
        assertEquals(
                "DECLARE @table TABLE (\nFIELD1 float,\nFIELD2 bit,\nFIELD3 varchar(MAX)\n);\nINSERT INTO @table\nSELECT NULL, NULL, 'Hello' UNION ALL\nSELECT 1.23, NULL, NULL;\nMERGE INTO [TABLE] AS [old]\nUSING @table AS [new] ON (\n[old].[FIELD1] = [new].[FIELD1]\n)\nWHEN MATCHED THEN\nUPDATE SET [FIELD1] = [new].[FIELD1], [FIELD2] = [new].[FIELD2], [FIELD3] = [new].[FIELD3]\nWHEN NOT MATCHED BY TARGET THEN\nINSERT ([FIELD1], [FIELD2], [FIELD3]) VALUES ([new].[FIELD1], [new].[FIELD2], [new].[FIELD3])\nOUTPUT INSERTED.[FIELD1], INSERTED.[FIELD2], INSERTED.[FIELD3];",
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

    @Test (expected = BadParameterException.class)
    public void noCSVTitle() {
        m.merge("TABLE").values(new CSVSet());
    }

    @Test (expected = BadParameterException.class)
    public void emptyCSV() {
        CSVSet data = new CSVSet();
        data.addTitle(new CSVRow(new String[] { "A" }));

        m.merge("TABLE").values(data);
    }

    @Test
    public void mergeCSV() {
        CSVSet data = new CSVSet();
        data.addTitle(new CSVRow(new String[] { "FIELD1", "FIELD2", "FIELD3" }));
        data.add(new CSVRow(new String[] { "1", "value", "" }));

        assertEquals(
                "DECLARE @table TABLE (\nFIELD1 int,\nFIELD2 varchar(MAX),\nFIELD3 bit\n);\nINSERT INTO @table\nSELECT 1, 'value', NULL;\nMERGE INTO [TABLE] AS [old]\nUSING @table AS [new] ON (\n[old].[FIELD1] = [new].[FIELD1]\n)\nWHEN MATCHED THEN\nUPDATE SET [FIELD2] = [new].[FIELD2], [FIELD3] = [new].[FIELD3]\nWHEN NOT MATCHED BY TARGET THEN\nINSERT ([FIELD2], [FIELD3]) VALUES ([new].[FIELD2], [new].[FIELD3])\nOUTPUT INSERTED.[FIELD1], INSERTED.[FIELD2], INSERTED.[FIELD3];",
                m.merge("TABLE")
                        .values(data)
                        .matching("FIELD1")
                        .omit("FIELD1")
                .getSql()
        );
    }
}
