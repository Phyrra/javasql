package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.helper.OrderParser;
import ch.sama.sql.query.helper.Value;

import java.util.List;

public class TSqlOrderParser implements OrderParser {
    private String getBase(List<Value> values) {
        StringBuilder builder = new StringBuilder();
        String prefix = "";

        for (Value v : values) {
            builder.append(prefix);
            builder.append(v.getString());

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
