package ch.sama.sql.jpa;

import ch.sama.sql.dialect.tsql.schema.TSqlSchemaRenderer;
import ch.sama.sql.query.exception.BadSqlException;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JpaSchemaTest {
    @Entity
    private static class Table1 {
        @Column(name = "COLUMN1")
        private String column1;

        @Column(name = "COLUMN2")
        private double column2;

        @Column(name = "COLUMN3")
        private int column3;

        @Column(name = "COLUMN4")
        private boolean column4;

        @Column(name = "COLUMN5")
        private Date column5;
    }

    @Test
    public void classToType() {
        assertEquals(
                "CREATE TABLE [Table1] (\n\t[COLUMN1] [varchar](MAX) NULL,\n\t[COLUMN2] [float] NULL,\n\t[COLUMN3] [int] NULL,\n\t[COLUMN4] [bit] NULL,\n\t[COLUMN5] [datetime] NULL\n)",
                TSqlSchemaRenderer.getTableSchema(Table1.class)
        );
    }

    @Entity
    private static class Table2 {
        @Column(name = "COLUMN1", nullable = true)
        private String column1;

        @Column(name = "COLUMN2", nullable = false)
        private String column2;
    }

    @Test
    public void nullable() {
        assertEquals(
                "CREATE TABLE [Table2] (\n\t[COLUMN1] [varchar](MAX) NULL,\n\t[COLUMN2] [varchar](MAX) NOT NULL\n)",
                TSqlSchemaRenderer.getTableSchema(Table2.class)
        );
    }

    @Entity
    private static class Table3 {
        @Column(name = "P_KEY", nullable = false)
        @AutoIncrement
        @PrimaryKey
        private int i;
    }

    @Test
    public void autoIncrementPrimary() {
        assertEquals(
                "CREATE TABLE [Table3] (\n\t[P_KEY] [int] IDENTITY(1,1) NOT NULL,\n\tCONSTRAINT [PK_Table3] PRIMARY KEY CLUSTERED (\n\t\t[P_KEY] ASC\n\t)\n) ON [PRIMARY]",
                TSqlSchemaRenderer.getTableSchema(Table3.class)
        );
    }

    private static class Table4 {
        @Column(name = "COLUMN")
        private String column;
    }

    @Test(expected = JpaException.class)
    public void notAnnotated() {
        TSqlSchemaRenderer.getTableSchema(Table4.class);
    }

    @Entity(name = "tblTable5")
    private static class Table5 {
        @Column(name = "COLUMN")
        private String column;
    }

    @Test
    public void definedTableName() {
        assertEquals(
                "CREATE TABLE [tblTable5] (\n\t[COLUMN] [varchar](MAX) NULL\n)",
                TSqlSchemaRenderer.getTableSchema(Table5.class)
        );
    }

    @Entity
    private static class Table6 {
        @Column(name = "AUTO")
        @AutoIncrement
        private double d;
    }

    @Test(expected = BadSqlException.class)
    public void autoIncrOnDouble() {
        TSqlSchemaRenderer.getTableSchema(Table6.class);
    }

    @Entity
    private static class Table7 {
        @Column(name = "COLUMN")
        private List<String> list;
    }

    @Test(expected = BadSqlException.class)
    public void unknownType() {
        TSqlSchemaRenderer.getTableSchema(Table7.class);
    }
}