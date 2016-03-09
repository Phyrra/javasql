package ch.sama.sql.dbo.schema;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;

import java.util.ArrayList;
import java.util.List;

public class SchemaUtil {
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
