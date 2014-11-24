package ch.sama.sql.tsql.dialect;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.*;
import ch.sama.sql.query.exception.UnknownValueException;
import ch.sama.sql.query.helper.Function;
import ch.sama.sql.query.helper.condition.IConditionRenderer;
import ch.sama.sql.query.helper.order.IOrder;
import ch.sama.sql.query.helper.order.IOrderRenderer;
import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.Value;

import java.util.List;

class TSqlQueryRenderer implements IQueryRenderer {
    private IConditionRenderer conditionParser;
    private IOrderRenderer orderParser;

    public TSqlQueryRenderer() {
        conditionParser = new TSqlConditionRenderer(this);
        orderParser = new TSqlOrderRenderer();
    }

    @Override
    public String render(Query query) {
        IQuery parent = query.getParent();
        if (parent != null) {
            return parent.getSql() + " UNION ALL";
        }

        return null;
    }

    @Override
    public String render(CTEQuery query) {
        StringBuilder builder = new StringBuilder();

        IQuery parent = query.getParent();
        if (parent instanceof CTEQueryFinal) {
            builder.append(parent.getSql());
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
        builder.append(parent.getSql());

        builder.append(" AS (\n");
        builder.append(query.getQuery().getSql());
        builder.append("\n)");

        return builder.toString();
    }

    @Override
    public String render(SelectQuery query) {
        StringBuilder builder = new StringBuilder();

        IQuery parent = query.getParent();
        if (parent != null) {
            String parentQuery = parent.getSql();
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

        builder.append(query.getParent().getSql());

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

        builder.append(query.getParent().getSql());

        builder.append("\n");

        JoinQuery.TYPE type = query.getType();
        if (type != null) {
            switch (type) {
                case LEFT:
                    builder.append("LEFT");
                    break;
                case RIGHT:
                    builder.append("RIGHT");
                    break;
                default:
                    throw new UnknownValueException("Unknown join type: " + type);
            }

            builder.append(" ");
        }
        builder.append("JOIN ");

        builder.append(render(query.getSource()));

        return builder.toString();
    }

    @Override
    public String render(JoinQueryFinal query) {
        StringBuilder builder = new StringBuilder();

        builder.append(query.getParent().getSql());

        builder.append(" ON ");
        builder.append(query.getCondition().render(conditionParser));

        return builder.toString();
    }

    @Override
    public String render(WhereQuery query) {
        StringBuilder builder = new StringBuilder();

        builder.append(query.getParent().getSql());
        builder.append("\nWHERE ");
        builder.append(query.getCondition().render(conditionParser));

        return builder.toString();
    }

    @Override
    public String render(OrderQuery query) {
        StringBuilder builder = new StringBuilder();

        IQuery parent = query.getParent();
        builder.append(parent.getSql());

        builder.append("\nORDER BY ");

        String prefix = "";
        List<IOrder> orders = query.getOrders();
        for (IOrder o : orders) {
            builder.append(prefix);
            builder.append(o.render(orderParser));

            prefix = ", ";
        }

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

    @Override
    public String render(Function f) {
        return f.getDefaultString();
    }
}
