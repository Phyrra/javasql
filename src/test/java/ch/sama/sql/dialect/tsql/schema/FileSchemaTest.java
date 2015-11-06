package ch.sama.sql.dialect.tsql.schema;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.dialect.tsql.type.TYPE;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FileSchemaTest {
    private File getResource(String path) throws FileNotFoundException {
        URL url = getClass().getClassLoader().getResource(path);
        if (url == null) {
            throw new FileNotFoundException("File " + path + " not found");
        }

        return new File(url.getPath());
    }

    private File getSchemaFile(String name) {
        File file = null;

        try {
            file = getResource("schema/" + name + ".sql");
        } catch (FileNotFoundException e) {
            // ignore
        }

        assertNotEquals(null, file);
        return file;
    }

    private TSqlSchema getSchema(String name) {
        File file = getSchemaFile(name);

        return new TSqlSchema(file);
    }

    @Test
    public void basicTable() {
        TSqlSchema schema = getSchema("BasicTable");

        assertEquals(true, schema.hasTable("tblTable"));

        Table table = schema.getTable("tblTable");

        List<Field> primaryKey = table.getPrimaryKey();
        assertEquals(1, primaryKey.size());

        assertEquals("iId", primaryKey.get(0).getName());

        assertEquals(true, table.hasColumn("iId"));

        Field id = table.getColumn("iId");
        assertEquals(true, id.isPrimaryKey());
        assertEquals(false, id.isNullable());
        assertEquals(true, TYPE.isWeakEqualType(id.getDataType(), TYPE.INT_TYPE));

        assertEquals(true, table.hasColumn("sName"));

        Field name = table.getColumn("sName");
        assertEquals(false, name.isNullable());
        assertEquals(true, TYPE.isWeakEqualType(name.getDataType(), TYPE.VARCHAR_TYPE(50)));

        assertEquals(true, table.hasColumn("sLastName"));

        Field lastName = table.getColumn("sLastName");
        assertEquals(true, lastName.isNullable());
        assertEquals(true, TYPE.isWeakEqualType(lastName.getDataType(), TYPE.VARCHAR_TYPE(50)));
    }

    @Test
    public void withAutoIncrement() {
        TSqlSchema schema = getSchema("TableWithAutoIncrement");

        Table table = schema.getTable("tblTable");

        assertEquals(true, table.hasColumn("iId"));

        Field id = table.getColumn("iId");
        assertEquals(true, id.isAutoIncrement());
        assertEquals(false, id.isNullable());
        assertEquals(true, TYPE.isWeakEqualType(id.getDataType(), TYPE.INT_TYPE));
    }

    @Test
    public void withDefaultValue() {
        TSqlSchema schema = getSchema("TableWithDefault");

        Table table = schema.getTable("tblTable");

        assertEquals(true, table.hasColumn("uidId"));

        Field id = table.getColumn("uidId");
        assertEquals(true, id.hasDefaultValue());
        assertEquals("newsequentialid()", id.getDefaultValue().getValue());
        assertEquals(false, id.isNullable());
        assertEquals(true, TYPE.isWeakEqualType(id.getDataType(), TYPE.UNIQUEIDENTIFIER_TYPE));
    }
}
