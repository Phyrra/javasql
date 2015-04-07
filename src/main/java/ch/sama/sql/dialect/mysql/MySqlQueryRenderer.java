package ch.sama.sql.dialect.mysql;

import ch.sama.sql.dialect.mysql.query.MySqlLimitQuery;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.order.IOrder;
import ch.sama.sql.query.standard.QueryRenderer;

public class MySqlQueryRenderer extends QueryRenderer {
    private static final MySqlConditionRenderer conditionRenderer = new MySqlConditionRenderer();
    private static final MySqlOrderRenderer orderRenderer = new MySqlOrderRenderer();

    @Override
    public String render(ICondition condition) {
        return condition.render(conditionRenderer);
    }

    @Override
    public String render(IOrder order) {
        return order.render(orderRenderer);
    }

    @Override
    public String renderObjectName(String s) {
        return "`" + s + "`";
    }

    public String render(MySqlLimitQuery query) {
        StringBuilder builder = new StringBuilder();

        builder.append(query.getParent().getSql());

        builder.append("\nLIMIT ");

        if (query.hasLimit()) {
            builder.append(query.getLimit());
        } else {
            int[] range = query.getRange();

            builder.append(range[0]);
            builder.append(", ");
            builder.append(range[1]);
        }

        return builder.toString();
    }
}
