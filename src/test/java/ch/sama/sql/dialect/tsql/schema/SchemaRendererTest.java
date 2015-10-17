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

    // TODO: Tables
}
