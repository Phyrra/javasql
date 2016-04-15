package ch.sama.sql.dbo.schema;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;

import java.util.ArrayList;
import java.util.List;

public class SchemaUtil {
    private static class NewTable implements ISchemaDiff {
        private Table table;

        NewTable(Table table) {
            this.table = table;
        }

        @Override
        public String getString(ISchemaRenderer renderer) {
            return renderer.render(table);
        }
    }

    private static class NewField implements ISchemaDiff {
        private Field field;

        NewField(Field field) {
            this.field = field;
        }

        @Override
        public String getString(ISchemaRenderer renderer) {
            return "ALTER TABLE " + renderer.renderName(field.getTable()) + " ADD " + renderer.render(field);
        }
    }

    private static class ChangeField implements ISchemaDiff {
        private Field field;

        ChangeField(Field field) {
            this.field = field;
        }

        @Override
        public String getString(ISchemaRenderer renderer) {
            return "ALTER TABLE " + renderer.renderName(field.getTable()) + " ALTER COLUMN " + renderer.render(field);
        }
    }

    public static List<ISchemaDiff> diff(ISchema lhs, ISchema rhs) {
        // lhs: actual
        // rhs: expected

        // This only executes non-destructive updates
        //  - No drop table
        //  - No drop column
        //  - No change of PRIMARY KEY (that would require DROP and re-CREATE)

        List<ISchemaDiff> diff = new ArrayList<ISchemaDiff>();

        for (Table tr : rhs.getTables()) {
            String rhTableName = tr.getName();

            if (lhs.hasTable(rhTableName)) {
                Table tl = lhs.getTable(rhTableName);

                for (Field fr : tr.getColumns()) {
                    String rhColumnName = fr.getName();

                    if (tl.hasColumn(rhColumnName)) {
                        Field fl = tl.getColumn(rhColumnName);

                        if (!fr.compareTo(fl)) {
                            diff.add(new ChangeField(fr));
                        }
                    } else {
                        diff.add(new NewField(fr));
                    }
                }
            } else {
                diff.add(new NewTable(tr));
            }
        }

        return diff;
    }
}
