package ch.sama.sql.tsql.dialect;

import static org.junit.Assert.*;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.exception.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

public class SchemaTest {
    // Table

    @Test
    public void tableNameAndSchema() {
        List<Table> list = new TSqlSchema("CREATE TABLE [dbo].[tblTable](").getTables();

        assertEquals(1, list.size());
        assertEquals("[dbo].[tblTable]", list.get(0).toString());
    }

    @Test
    public void tableNameOnly() {
        List<Table> list = new TSqlSchema("CREATE TABLE [tblTable](").getTables();

        assertEquals(1, list.size());
        assertEquals("[tblTable]", list.get(0).toString());
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
        assertEquals("int", field.getDataType());
        assertEquals(true, field.getNullable());
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
        assertEquals("varchar(50)", field.getDataType());
        assertEquals(true, field.getNullable());
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
        assertEquals("int", field.getDataType());
        assertEquals(false, field.getNullable());
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
        assertEquals("int", field.getDataType());
        assertEquals(false, field.getNullable());
        assertEquals("(1337)", field.getDefault().toString());
    }

    // PKey

    @Test
    public void primaryKey() {
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

        assertEquals(table.getColumn("uidId").toString(), table.getPrimaryKey().toString());
    }

    @Test (expected = ObjectNotFoundException.class)
    public void unknownPrimary() {
        List<Table> list = new TSqlSchema(
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
