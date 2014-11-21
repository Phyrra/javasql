package ch.sama.sql.tsql.dialect;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
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
            builder.append(render(v));

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
            builder.append(render(s));

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

        builder.append(render(query.getSource()));

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

    @Override
    public String render(Field f) {
        StringBuilder builder = new StringBuilder();

        Table table = f.getTable();
        String tableName = f.getTableName();

        if (table != null) {
            builder.append(table.getString(this));
            builder.append(".");
        } else if (tableName != null) {
            builder.append("[");
            builder.append(tableName);
            builder.append("].");
        }

        builder.append("[");
        builder.append(f.getName());
        builder.append("]");

        return builder.toString();
    }

    @Override
    public String render(Table t) {
        StringBuilder builder = new StringBuilder();

        String schema = t.getSchema();
        if (schema != null) {
            builder.append("[");
            builder.append(schema);
            builder.append("].");
        }

        builder.append("[");
        builder.append(t.getName());
        builder.append("]");

        return builder.toString();
    }

    @Override
    public String render(Value v) {
        StringBuilder builder = new StringBuilder();

        builder.append(v.getValue());

        String alias = v.getAlias();
        if (alias != null) {
            builder.append(" AS [");
            builder.append(alias);
            builder.append("]");
        }

        return builder.toString();
    }

    @Override
    public String render(Source s) {
        StringBuilder builder = new StringBuilder();

        builder.append(s.getValue());

        String alias = s.getAlias();
        if (alias != null) {
            builder.append(" AS [");
            builder.append(alias);
            builder.append("]");
        }

        return builder.toString();
    }
}
