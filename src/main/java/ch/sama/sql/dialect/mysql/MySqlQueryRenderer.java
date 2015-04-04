package ch.sama.sql.dialect.mysql;

import ch.sama.sql.dialect.mysql.query.MySqlLimitQuery;
import ch.sama.sql.dialect.tsql.TSqlConditionRenderer;
import ch.sama.sql.dialect.tsql.TSqlOrderRenderer;
import ch.sama.sql.dialect.tsql.query.TSqlCteQuery;
import ch.sama.sql.dialect.tsql.query.TSqlCteQueryFinal;
import ch.sama.sql.dialect.tsql.query.TSqlSelectQuery;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.condition.IConditionRenderer;
import ch.sama.sql.query.helper.order.IOrder;
import ch.sama.sql.query.helper.order.IOrderRenderer;
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
        builder.append(query.getStart());
        builder.append(", ");
        builder.append(query.getStop());

        return builder.toString();
    }
}
