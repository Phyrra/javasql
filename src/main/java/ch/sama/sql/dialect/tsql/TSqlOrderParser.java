package ch.sama.sql.dialect.tsql;

import ch.sama.sql.query.helper.Order;
import ch.sama.sql.query.helper.OrderParser;
import ch.sama.sql.query.helper.Value;

import java.util.List;

public class TSqlOrderParser implements OrderParser {
    private String getBase(List<Value> values) {
        StringBuilder builder = new StringBuilder();
        String prefix = "";

        for (Value v : values) {
            builder.append(prefix);
            builder.append(v.toString());

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
