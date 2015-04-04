package ch.sama.sql.dialect.tsql.query;

import static org.junit.Assert.*;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.exception.ObjectNotFoundException;
import ch.sama.sql.dialect.tsql.TSqlQueryBuilder;
import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.dialect.tsql.TSqlSchema;
import org.junit.Test;

import java.util.List;

public class SchemaTest {
    private static final TSqlQueryBuilder sql = new TSqlQueryBuilder();
    private static final TSqlQueryRenderer renderer = sql.renderer();

    // Table

    @Test
    public void tableNameAndSchema() {
        List<Table> list = new TSqlSchema("CREATE TABLE [dbo].[tblTable](").getTables();

        assertEquals(1, list.size());
        assertEquals("[dbo].[tblTable]", renderer.render(list.get(0)));
    }

    @Test
    public void tableNameOnly() {
        List<Table> list = new TSqlSchema("CREATE TABLE [tblTable](").getTables();

        assertEquals(1, list.size());
        assertEquals("[tblTable]", renderer.render(list.get(0)));
    }

    @Test (expected = BadSqlException.class)
    public void tableNoName() {
        new TSqlSchema("CREATE TABLE (");
    }

    @Test
    public void multipleTables() {
        List<Table> list = new TSqlSchema(
                "CREATE TABLE [dbo].[tblTable1](\n" +
                ")\n" +
                "CREATE TABLE [dbo].[tblTable2](\n" +
                ")"
        ).getTables();

        assertEquals(2, list.size());
    }

    // Fields

    @Test
    public void fieldSimple() {
        List<Table> list = new TSqlSchema(
                "CREATE TABLE [tblTable](\n" +
                "   [field]\n" +
                ")"
        ).getTables();

        Table table = list.get(0);
        List<Field> columns = table.getColumns();

        assertEquals(1, columns.size());
        assertEquals("field", columns.get(0).getName());
    }

    @Test (expected = BadSqlException.class)
    public void fieldNoTable() {
        new TSqlSchema("[field]");
    }

    @Test
    public void fieldDatatype() {
        List<Table> list = new TSqlSchema(
                "CREATE TABLE [tblTable](\n" +
                "   [iField] [int]\n" +
                ")"
        ).getTables();

        Table table = list.get(0);
        List<Field> columns = table.getColumns();
        Field field = columns.get(0);

        assertEquals("iField", field.getName());
        assertEquals("int", field.getDataType().getString());
        assertEquals(true, field.isNullable());
    }

    @Test
    public void fieldVarcharLength() {
        List<Table> list = new TSqlSchema(
                "CREATE TABLE [tblTable](\n" +
                "   [sField] [varchar](50)\n" +
                ")"
        ).getTables();

        Table table = list.get(0);
        List<Field> columns = table.getColumns();
        Field field = columns.get(0);

        assertEquals("sField", field.getName());
        assertEquals("varchar(50)", field.getDataType().getString());
        assertEquals(true, field.isNullable());
    }

    @Test
    public void fieldVarcharMax() {
        List<Table> list = new TSqlSchema(
                "CREATE TABLE [tblTable](\n" +
                "   [sField] [varchar](max)\n" +
                ")"
        ).getTables();

        Table table = list.get(0);
        List<Field> columns = table.getColumns();
        Field field = columns.get(0);

        assertEquals("sField", field.getName());
        assertEquals("varchar(MAX)", field.getDataType().getString());
        assertEquals(true, field.isNullable());
    }

    @Test
    public void fieldNotNullable() {
        List<Table> list = new TSqlSchema(
                "CREATE TABLE [tblTable](\n" +
                "   [iField] [int] NOT NULL\n" +
                ")"
        ).getTables();

        Table table = list.get(0);
        List<Field> columns = table.getColumns();
        Field field = columns.get(0);

        assertEquals("iField", field.getName());
        assertEquals("int", field.getDataType().getString());
        assertEquals(false, field.isNullable());
    }

    @Test
    public void fieldDefault() {
        List<Table> list = new TSqlSchema(
                "CREATE TABLE [tblTable](\n" +
                "   [iField] [int] NOT NULL CONSTRAINT [DF_tblTable_iField] DEFAULT (1337)\n" +
                ")"
        ).getTables();

        Table table = list.get(0);
        List<Field> columns = table.getColumns();
        Field field = columns.get(0);

        assertEquals("iField", field.getName());
        assertEquals("int", field.getDataType().getString());
        assertEquals(false, field.isNullable());
        assertEquals("(1337)", field.getDefaultValue().getValue());
    }

    @Test
    public void fieldDefaultFunction() {
        List<Table> list = new TSqlSchema(
                "CREATE TABLE [tblTable](\n" +
                "   [uidId] [uniqueidentifier] NOT NULL CONSTRAINT [DF_tblTable_uidId] DEFAULT (newsequentialid())\n" +
                ")"
        ).getTables();

        Table table = list.get(0);
        List<Field> columns = table.getColumns();
        Field field = columns.get(0);

        assertEquals("uidId", field.getName());
        assertEquals("uniqueidentifier", field.getDataType().getString());
        assertEquals(false, field.isNullable());
        assertEquals("(newsequentialid())", field.getDefaultValue().getValue());
    }

    // PKey

    @Test
    public void singlePrimaryKey() {
        List<Table> list = new TSqlSchema(
                "CREATE TABLE [tblTable](\n" +
                "   [uidId] [uniqueidentifier] NOT NULL CONSTRAINT [DF_tblTable_uidId] DEFAULT (newsequentialid()),\n" +
                "   [iField] [int] NULL,\n" +
                "   CONSTRAINT [PK_tblTable] PRIMARY KEY CLUSTERED (\n" +
                "       [uidId] ASC\n" +
                "   )\n" +
                ") ON [PRIMARY]"
        ).getTables();

        Table table = list.get(0);

        assertEquals(
                renderer.render(table.getColumn("uidId")),
                renderer.render(table.getPrimaryKey().get(0))
        );
    }

    @Test
    public void multiPrimaryKey() {
        List<Table> list = new TSqlSchema(
                "CREATE TABLE [tblTable](\n" +
                "   [uidId1] [uniqueidentifier] NOT NULL CONSTRAINT [DF_tblTable_uidId] DEFAULT (newsequentialid()),\n" +
                "   [uidId2] [uniqueidentifier] NOT NULL CONSTRAINT [DF_tblTable_uidId] DEFAULT (newsequentialid()),\n" +
                "   [iField] [int] NULL,\n" +
                "   CONSTRAINT [PK_tblTable] PRIMARY KEY CLUSTERED (\n" +
                "       [uidId1] ASC,\n" +
                "       [uidId2] ASC\n" +
                "   )\n" +
                ") ON [PRIMARY]"
        ).getTables();

        Table table = list.get(0);

        assertEquals(2, table.getPrimaryKey().size());

        // Order of keys is not kept
        assertEquals(true, table.getColumn("uidId1").isPrimaryKey());
        assertEquals(true, table.getColumn("uidId2").isPrimaryKey());
    }

    @Test (expected = ObjectNotFoundException.class)
    public void unknownPrimary() {
        new TSqlSchema(
                "CREATE TABLE [tblTable](\n" +
                "   [uidId] [uniqueidentifier] NOT NULL CONSTRAINT [DF_tblTable_uidId] DEFAULT (newsequentialid()),\n" +
                "   [iField] [int] NULL,\n" +
                "   CONSTRAINT [PK_tblTable] PRIMARY KEY CLUSTERED (\n" +
                "       [id] ASC\n" +
                "   )\n" +
                ") ON [PRIMARY]"
        ).getTables();
    }
}