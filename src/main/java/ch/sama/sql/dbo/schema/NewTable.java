package ch.sama.sql.dbo.schema;

import ch.sama.sql.dbo.Table;

class NewTable extends TableDiff {
    private Table table;

    NewTable(Table table) {
        this.table = table;
    }

    public String getString(ISchemaRenderer renderer) {
        return renderer.render(table);
    }
}
