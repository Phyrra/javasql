package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.helper.IOrderParser;
import ch.sama.sql.query.helper.Value;

import java.util.List;

class TSqlOrderParser implements IOrderParser {
    private String getBase(List<Value> values) {
        StringBuilder builder = new StringBuilder();
        String prefix = "";

        for (Value v : values) {
            builder.append(prefix);
            builder.append(v.getValue());

            prefix = ", ";
        }

        return builder.toString();
    }

    @Override
    public String asc(List<Value> values) {
        return getBase(values) + " ASC";
    }

    @Override
    public String desc(List<Value> values) {
        return getBase(values) + " DESC";
    }
}
