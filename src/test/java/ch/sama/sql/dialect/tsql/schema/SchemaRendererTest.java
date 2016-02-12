package ch.sama.sql.dialect.tsql.schema;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import ch.sama.sql.dialect.tsql.type.TYPE;
import ch.sama.sql.query.exception.BadSqlException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SchemaRendererTest {
    private static final TSqlSchemaRenderer renderer = new TSqlSchemaRenderer();
    private static final TSqlValueFactory value = new TSqlValueFactory();

    // Field

    @Test
    public void renderTableNameWithSchema() {
        assertEquals("[dbo].[table]", renderer.renderName(new Table("dbo", "table")));
    }

    @Test
    public void renderTableNameNoSchema() {
        assertEquals("[table]", renderer.renderName(new Table("table")));
    }

    @Test
    public void renderFieldName() {
        assertEquals("[field]", renderer.renderName(new Field("table", "field")));
    }

    @Test (expected = BadSqlException.class)
    public void renderColumnNoType() {
        renderer.render(
                new Field("table", "field")
        );
    }

    @Test (expected = BadSqlException.class)
    public void renderColumnBadAutoincrement() {
        renderer.render(
                new Field("table", "field")
                        .chainType(TYPE.BIT_TYPE)
                        .chainAutoIncrement(true)
        );
    }

    @Test
    public void renderColumn() {
        assertEquals(
                "[field] [int] NULL",
                renderer.render(
                        new Field("table", "field")
                                .chainType(TYPE.INT_TYPE)
                )
        );
    }

    @Test
    public void renderColumnNotNull() {
        assertEquals(
                "[field] [int] NOT NULL",
                renderer.render(
                        new Field("table", "field")
                                .chainType(TYPE.INT_TYPE)
                                .chainNullable(false)
                )
        );
    }

    @Test
    public void renderColumnAutoIncrement() {
        assertEquals(
                "[field] [int] IDENTITY(1,1) NOT NULL",
                renderer.render(
                        new Field("table", "field")
                                .chainType(TYPE.INT_TYPE)
                                .chainNullable(false)
                                .chainAutoIncrement(true)
                )
        );
    }

    @Test
    public void renderColumnDefaultValue() {
        assertEquals(
                "[field] [int] NULL CONSTRAINT [DF_table_field] DEFAULT (-1)",
                renderer.render(
                        new Field("table", "field")
                                .chainType(TYPE.INT_TYPE)
                                .chainDefaultValue(value.numeric(-1))
                )
        );
    }

    @Test
    public void renderVarcharMaxColumn() {
        assertEquals(
                "[field] [varchar](MAX) NOT NULL",
                renderer.render(
                        new Field("table", "field")
                                .chainType(TYPE.VARCHAR_MAX_TYPE)
                                .chainNullable(false)
                )
        );
    }

    // Table

    @Test
    public void renderTableWithoutColumns() {
        assertEquals(
                "CREATE TABLE [table] (\n)",
                renderer.render(
                        new TSqlTableBuilder("table").getTable()
                )
        );
    }

    @Test
    public void renderTableWithOneColumn() {
        assertEquals(
                "CREATE TABLE [table] (\n\t[field] [int] NULL\n)",
                renderer.render(
                        new TSqlTableBuilder("table")
                                .addColumn(new Field("field").chainType(TYPE.INT_TYPE))
                        .getTable()
                )
        );
    }

    @Test
    public void renderTableWithManyColumns() {
        assertEquals(
                "CREATE TABLE [table] (\n\t[field1] [int] NULL,\n\t[field2] [int] NULL\n)",
                renderer.render(
                        new TSqlTableBuilder("table")
                                .addColumn(new Field("field1").chainType(TYPE.INT_TYPE))
                                .addColumn(new Field("field2").chainType(TYPE.INT_TYPE))
                                .getTable()
                )
        );
    }

    @Test
    public void renderTableWithOnePrimaryKey() {
        assertEquals(
                "CREATE TABLE [table] (\n\t[id] [int] IDENTITY(1,1) NOT NULL,\n\t[field] [varchar](MAX) NULL,\n\tCONSTRAINT [PK_table] PRIMARY KEY CLUSTERED (\n\t\t[id] ASC\n\t)\n) ON [PRIMARY]",
                renderer.render(
                        new TSqlTableBuilder("table")
                                .addColumn(
                                        new Field("id")
                                                .chainType(TYPE.INT_TYPE)
                                                .chainAutoIncrement(true)
                                                .chainNullable(false)
                                                .chainPrimaryKey(true)
                                )
                                .addColumn(
                                        new Field("field")
                                                .chainType(TYPE.VARCHAR_MAX_TYPE)
                                )
                        .getTable()
                )
        );
    }

    @Test
    public void renderTableWithMultiPrimaryKeys() {
        assertEquals(
                "CREATE TABLE [table] (\n\t[id1] [int] IDENTITY(1,1) NOT NULL,\n\t[id2] [int] IDENTITY(1,1) NOT NULL,\n\t[field] [varchar](MAX) NULL,\n\tCONSTRAINT [PK_table] PRIMARY KEY CLUSTERED (\n\t\t[id1] ASC,\n\t\t[id2] ASC\n\t)\n) ON [PRIMARY]",
                renderer.render(
                        new TSqlTableBuilder("table")
                                .addColumn(
                                        new Field("id1")
                                                .chainType(TYPE.INT_TYPE)
                                                .chainAutoIncrement(true)
                                                .chainNullable(false)
                                                .chainPrimaryKey(true)
                                )
                                .addColumn(
                                        new Field("id2")
                                                .chainType(TYPE.INT_TYPE)
                                                .chainAutoIncrement(true)
                                                .chainNullable(false)
                                                .chainPrimaryKey(true)
                                )
                                .addColumn(
                                        new Field("field")
                                                .chainType(TYPE.VARCHAR_MAX_TYPE)
                                )
                        .getTable()
                )
        );
    }
}
