package ch.sama.sql.dialect.tsql;

import ch.sama.sql.query.helper.Order;
import ch.sama.sql.query.helper.OrderParser;
import ch.sama.sql.query.helper.Value;

public class TSqlOrderParser implements OrderParser {
    private String getBase(Order o) {
        StringBuilder builder = new StringBuilder();
        String prefix = "";

        builder.append("ORDER BY ");

        for (Value v : o.getValues()) {
            builder.append(prefix);
            builder.append(v.toString());

            prefix = ", ";
        }

        return builder.toString();
    }

    @Override
    public String asc(Order o) {
        return getBase(o) + " ASC";
    }

    @Override
    public String desc(Order o) {
        return getBase(o) + " DESC";
    }
}
