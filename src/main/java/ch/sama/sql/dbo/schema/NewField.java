package ch.sama.sql.dbo.schema;

import ch.sama.sql.dbo.Field;

class NewField extends FieldDiff {
    private Field field;

    NewField(Field field) {
        this.field = field;
    }

    public String getString(ISchemaRenderer renderer) {
        return "ALTER TABLE " + renderer.renderName(field.getTable()) + " ADD " + renderer.render(field);
    }
}
