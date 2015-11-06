package ch.sama.sql.dbo.schema;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;

public interface ISchemaRenderer {
    public String renderName(Table table);
    public String renderName(Field field);

    public String render(Table table);
    public String render(Field field);
}
