package ch.sama.sql.dialect.tsql;

import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.order.IOrder;
import ch.sama.sql.query.standard.QueryRenderer;
import ch.sama.sql.dialect.tsql.query.TSqlCteQuery;
import ch.sama.sql.dialect.tsql.query.TSqlCteQueryFinal;
import ch.sama.sql.dialect.tsql.query.TSqlSelectQuery;

public class TSqlQueryRenderer extends QueryRenderer {
    private static final TSqlConditionRenderer conditionRenderer = new TSqlConditionRenderer();
    private static final TSqlOrderRenderer orderRenderer = new TSqlOrderRenderer();

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
        return "[" + s + "]";
    }

    public String render(TSqlSelectQuery query) {
        StringBuilder builder = new StringBuilder();

        appendIfExists(builder, query);

        builder.append("SELECT ");

        if (query.hasTop()) {
            builder.append("TOP ");
            builder.append(query.getTop());
            builder.append(" ");
        }

        String prefix = "";
        for (Value v : query.getValues()) {
            builder.append(prefix);
            builder.append(render(v));

            prefix = ", ";
        }

        return builder.toString();
    }

    public String render(TSqlCteQuery query) {
        StringBuilder builder = new StringBuilder();

        IQuery parent = query.getParent();
        if (parent instanceof TSqlCteQueryFinal) {
            builder.append(parent.getSql());
            builder.append(", ");
        } else {
            builder.append("WITH ");
        }

        builder.append(renderObjectName(query.getName()));

        return builder.toString();
    }

    public String render(TSqlCteQueryFinal query) {
        StringBuilder builder = new StringBuilder();

        IQuery parent = query.getParent();
        builder.append(parent.getSql());

        builder.append(" AS (\n");
        builder.append(query.getQuery().getSql());
        builder.append("\n)");

        return builder.toString();
    }
}
