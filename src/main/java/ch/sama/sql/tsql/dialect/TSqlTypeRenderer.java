package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.helper.type.*;

public class TSqlTypeRenderer implements ITypeRenderer {
    @Override
    public String render(VarcharType type) {
        if (type.hasMaxLength()) {
            int maxLength = type.getMaxLength();
            
            if (maxLength < 0) {
                return "varchar(MAX)";
            } else {
                return "varchar(" + type.getMaxLength() + ")";
            }
        } else {
            return "varchar";
        }
    }

    @Override
    public String render(IntType type) {
        return "int";
    }

    @Override
    public String render(FloatType type) {
        return "float";
    }

    @Override
    public String render(DatetimeType type) {
        return "datetime";
    }
}
