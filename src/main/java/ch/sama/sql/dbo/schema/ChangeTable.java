package ch.sama.sql.dbo.schema;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class ChangeTable extends TableDiff {
    private List<FieldDiff> fieldDiff;

    ChangeTable() {
        this.fieldDiff = new ArrayList<FieldDiff>();
    }

    ChangeTable(List<FieldDiff> fieldDiff) {
        this.fieldDiff = fieldDiff;
    }

    void addFieldDiff(FieldDiff diff) {
        fieldDiff.add(diff);
    }

    boolean hasChanges() {
        return !fieldDiff.isEmpty();
    }

    public String getString(ISchemaRenderer renderer) {
        return fieldDiff.stream()
                .map(d -> d.getString(renderer))
                .collect(Collectors.joining("\n"));
    }
}
