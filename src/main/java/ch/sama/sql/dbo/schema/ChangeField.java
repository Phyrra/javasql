package ch.sama.sql.dbo.schema;

import ch.sama.sql.dbo.Field;

class ChangeField implements ISchemaDiff {
    private Field field;

    ChangeField(Field field) {
        this.field = field;
    }

    public String getString(ISchemaRenderer renderer) {
        return "ALTER TABLE " + renderer.renderName(field.getTable()) + " ALTER COLUMN " + renderer.render(field);
    }
}
