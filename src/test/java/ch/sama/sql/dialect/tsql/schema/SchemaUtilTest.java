package ch.sama.sql.dialect.tsql.schema;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.dbo.schema.ISchemaDiff;
import ch.sama.sql.dbo.schema.SchemaUtil;
import ch.sama.sql.dialect.tsql.type.TYPE;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SchemaUtilTest {
    private static final TSqlSchemaRenderer renderer = new TSqlSchemaRenderer();

    @Test
    public void tableOnlyRight() {
        TSqlSchema lhs = new TSqlSchemaBuilder().getSchema();

        TSqlSchema rhs = new TSqlSchemaBuilder()
                .addTable(new Table("table"))
                .getSchema();

        List<ISchemaDiff> diff = SchemaUtil.diff(lhs, rhs);

        assertEquals(1, diff.size());

        assertEquals("CREATE TABLE [table] (\n)", diff.get(0).getString(renderer));
    }

    @Test
    public void tableOnlyLeft() {
        TSqlSchema lhs = new TSqlSchemaBuilder()
                .addTable(new Table("table"))
                .getSchema();

        TSqlSchema rhs = new TSqlSchemaBuilder().getSchema();

        List<ISchemaDiff> diff = SchemaUtil.diff(lhs, rhs);

        assertEquals(0, diff.size());
    }

    @Test
    public void sameTableLeftAndRight() {
        TSqlSchema lhs = new TSqlSchemaBuilder()
                .addTable(new Table("table"))
                .getSchema();

        TSqlSchema rhs = new TSqlSchemaBuilder()
                .addTable(new Table("table"))
                .getSchema();

        List<ISchemaDiff> diff = SchemaUtil.diff(lhs, rhs);

        assertEquals(0, diff.size());
    }

    @Test
    public void fieldOnlyRight() {
        TSqlSchema lhs = new TSqlSchemaBuilder()
                .addTable(new Table("table"))
                .getSchema();

        TSqlSchema rhs = new TSqlSchemaBuilder()
                .addTable(
                        new TSqlTableBuilder("table")
                                .addColumn(
                                        new Field("table", "field")
                                                .chainType(TYPE.INT_TYPE)
                                )
                        .getTable()
                )
                .getSchema();

        List<ISchemaDiff> diff = SchemaUtil.diff(lhs, rhs);

        assertEquals(1, diff.size());

        assertEquals("ALTER TABLE [table] ADD [field] [int] NULL", diff.get(0).getString(renderer));
    }

    @Test
    public void fieldOnlyLeft() {
        TSqlSchema lhs = new TSqlSchemaBuilder()
                .addTable(
                        new TSqlTableBuilder("table")
                                .addColumn(
                                        new Field("table", "field")
                                                .chainType(TYPE.INT_TYPE)
                                )
                                .getTable()
                )
                .getSchema();

        TSqlSchema rhs = new TSqlSchemaBuilder()
                .addTable(new Table("table"))
                .getSchema();

        List<ISchemaDiff> diff = SchemaUtil.diff(lhs, rhs);

        assertEquals(0, diff.size());
    }

    @Test
    public void sameFieldLeftAndRight() {
        TSqlSchema lhs = new TSqlSchemaBuilder()
                .addTable(
                        new TSqlTableBuilder("table")
                                .addColumn(
                                        new Field("table", "field")
                                                .chainType(TYPE.INT_TYPE)
                                )
                                .getTable()
                )
                .getSchema();

        TSqlSchema rhs = new TSqlSchemaBuilder()
                .addTable(
                        new TSqlTableBuilder("table")
                                .addColumn(
                                        new Field("table", "field")
                                                .chainType(TYPE.INT_TYPE)
                                )
                                .getTable()
                )
                .getSchema();

        List<ISchemaDiff> diff = SchemaUtil.diff(lhs, rhs);

        assertEquals(0, diff.size());
    }

    @Test
    public void changeInField() {
        TSqlSchema lhs = new TSqlSchemaBuilder()
                .addTable(
                        new TSqlTableBuilder("table")
                                .addColumn(
                                        new Field("table", "field")
                                                .chainType(TYPE.INT_TYPE)
                                )
                                .getTable()
                )
                .getSchema();

        TSqlSchema rhs = new TSqlSchemaBuilder()
                .addTable(
                        new TSqlTableBuilder("table")
                                .addColumn(
                                        new Field("table", "field")
                                                .chainType(TYPE.FLOAT_TYPE)
                                )
                                .getTable()
                )
                .getSchema();

        List<ISchemaDiff> diff = SchemaUtil.diff(lhs, rhs);

        assertEquals(1, diff.size());

        assertEquals("ALTER TABLE [table] ALTER COLUMN [field] [float] NULL", diff.get(0).getString(renderer));
    }
}
