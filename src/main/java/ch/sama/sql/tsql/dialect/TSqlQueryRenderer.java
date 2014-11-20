package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.base.*;
import ch.sama.sql.query.helper.ConditionParser;
import ch.sama.sql.query.helper.OrderParser;
import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.Value;

public class TSqlQueryRenderer implements IQueryRenderer {
    private ConditionParser conditionParser;
    private OrderParser orderParser;

    public TSqlQueryRenderer() {
        conditionParser = new TSqlConditionParser(this);
        orderParser = new TSqlOrderParser();
    }

    @Override
    public String render(Query query) {
        IQuery parent = query.getParent();
        if (parent != null) {
            return parent.getSql(this) + " UNION ALL";
        }

        return null;
    }

    @Override
    public String render(CTEQuery query) {
        StringBuilder builder = new StringBuilder();

        IQuery parent = query.getParent();
        if (parent instanceof CTEQueryFinal) {
            builder.append(parent.getSql(this));
            builder.append(", ");
        } else {
            builder.append("WITH ");
        }

        builder.append(query.getName());

        return builder.toString();
    }

    @Override
    public String render(CTEQueryFinal query) {
        StringBuilder builder = new StringBuilder();

        IQuery parent = query.getParent();
        builder.append(parent.getSql(this));

        builder.append(" AS (\n");
        builder.append(query.getQuery().getSql(this));
        builder.append("\n)");

        return builder.toString();
    }

    @Override
    public String render(SelectQuery query) {
        StringBuilder builder = new StringBuilder();

        IQuery parent = query.getParent();
        if (parent != null) {
            String parentQuery = parent.getSql(this);
            if (parentQuery != null) {
                builder.append(parentQuery);
                builder.append("\n");
            }
        }

        String prefix = "";

        builder.append("SELECT ");

        int topN = query.getTopN();
        if (topN > 0) {
            builder.append("TOP ");
            builder.append(topN);
            builder.append(" ");
        }

        for (Value v : query.getValues()) {
            builder.append(prefix);
            builder.append(v.getString());

            prefix = ", ";
        }

        return builder.toString();
    }

    @Override
    public String render(FromQuery query) {
        StringBuilder builder = new StringBuilder();

        builder.append(query.getParent().getSql(this));

        String prefix = "";

        builder.append("\nFROM ");

        for (Source s : query.getSources()) {
            builder.append(prefix);
            builder.append(s.getString());

            prefix = ", ";
        }

        return builder.toString();
    }

    @Override
    public String render(JoinQuery query) {
        StringBuilder builder = new StringBuilder();

        builder.append(query.getParent().getSql(this));

        builder.append("\n");

        String type = query.getType();
        if (type != null) {
            builder.append(type);
            builder.append(" ");
        }
        builder.append("JOIN ");

        builder.append(query.getSource().getString());

        return builder.toString();
    }

    @Override
    public String render(JoinQueryFinal query) {
        StringBuilder builder = new StringBuilder();

        builder.append(query.getParent().getSql(this));

        builder.append(" ON ");
        builder.append(query.getCondition().getString(conditionParser));

        return builder.toString();
    }

    @Override
    public String render(WhereQuery query) {
        StringBuilder builder = new StringBuilder();

        builder.append(query.getParent().getSql(this));
        builder.append("\nWHERE ");
        builder.append(query.getCondition().getString(conditionParser));

        return builder.toString();
    }

    @Override
    public String render(OrderQuery query) {
        StringBuilder builder = new StringBuilder();

        IQuery parent = query.getParent();
        builder.append(parent.getSql(this));

        if (parent instanceof OrderQuery) {
            builder.append(", ");
        } else {
            builder.append("\n");
            builder.append("ORDER BY ");
        }

        builder.append(query.getOrder().getString(orderParser));

        return builder.toString();
    }
}
