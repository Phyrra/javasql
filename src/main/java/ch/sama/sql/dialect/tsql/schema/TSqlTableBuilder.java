package ch.sama.sql.dialect.tsql.schema;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;

public class TSqlTableBuilder {
    private Table table;

    public TSqlTableBuilder(Table table) {
        this.table = table;
    }

    public TSqlTableBuilder(String table) {
        this.table = new Table(table);
    }

    public TSqlTableBuilder addColumn(Field field) {
        table.addColumn(field);

        return this;
    }

    public Table getTable() {
        return table;
    }
}
