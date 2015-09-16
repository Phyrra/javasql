package ch.sama.sql.query.standard;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.*;
import ch.sama.sql.query.exception.UnknownValueException;
import ch.sama.sql.query.helper.Function;
import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.order.IOrder;
import ch.sama.sql.dialect.tsql.query.TSqlSelectQuery;

import java.util.List;
import java.util.stream.Collectors;

public abstract class QueryRenderer implements IQueryRenderer {
    protected void prependParentIfExists(StringBuilder builder, IQuery query) {
        IQuery parent = query.getParent();

        if (parent != null) {
            String parentQuery = parent.getSql();

            if (parentQuery != null) {
                builder.append(parentQuery);
                builder.append("\n");
            }
        }
    }

    @Override
    public String render(Query query) {
        IQuery parent = query.getParent();

        if (parent != null) {
            String parentQuery = parent.getSql();

            if (parentQuery != null) {
                return parentQuery + "\nUNION ALL";
            }
        }

        return null;
    }

    @Override
    public String render(UnionQuery query) {
        StringBuilder builder = new StringBuilder();

        prependParentIfExists(builder, query);

        builder.append(
                query.getQueries().stream()
                        .map(IQuery::getSql)
                        .collect(Collectors.joining("\nUNION ALL\n"))
        );

        return builder.toString();
    }

    @Override
    public String render(SelectQuery query) {
        StringBuilder builder = new StringBuilder();

        prependParentIfExists(builder, query);

        builder.append("SELECT ");

        builder.append(
                query.getValues().stream()
                        .map(this::render)
                        .collect(Collectors.joining(", "))
        );

        return builder.toString();
    }

    public String render(TSqlSelectQuery query) {
        StringBuilder builder = new StringBuilder();

        prependParentIfExists(builder, query);

        builder.append("SELECT ");

        if (query.hasTop()) {
            builder.append("TOP ");
            builder.append(query.getTop());
            builder.append(" ");
        }

        builder.append(
                query.getValues().stream()
                        .map(this::render)
                        .collect(Collectors.joining(", "))
        );

        return builder.toString();
    }

    @Override
    public String render(FromQuery query) {
        StringBuilder builder = new StringBuilder();

        prependParentIfExists(builder, query);

        builder.append("FROM ");

        builder.append(
                query.getSources().stream()
                        .map(this::render)
                        .collect(Collectors.joining(", "))
        );

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
                case INNER:
                    builder.append("INNER");
                    break;
                case LEFT:
                    builder.append("LEFT");
                    break;
                case RIGHT:
                    builder.append("RIGHT");
                    break;
                case FULL:
                    builder.append("FULL");
                    break;
                case CROSS:
                    builder.append("CROSS");
                    break;
                default:
                    throw new UnknownValueException("Unknown join type: " + type);
            }

            builder.append(" ");
        } else {
            // builder.append("INNER "); // Don't really like it
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
        builder.append(render(query.getCondition()));

        return builder.toString();
    }

    @Override
    public String render(WhereQuery query) {
        StringBuilder builder = new StringBuilder();

        builder.append(query.getParent().getSql());
        builder.append("\nWHERE ");
        builder.append(render(query.getCondition()));

        return builder.toString();
    }

    @Override
    public String render(OrderQuery query) {
        StringBuilder builder = new StringBuilder();

        IQuery parent = query.getParent();
        builder.append(parent.getSql());

        builder.append("\nORDER BY ");

        builder.append(
                query.getOrders().stream()
                        .map(this::render)
                        .collect(Collectors.joining(", "))
        );

        return builder.toString();
    }

    @Override
    public String render(DeleteQuery query) {
        StringBuilder builder = new StringBuilder();

        prependParentIfExists(builder, query);

        return builder.toString();
    }

    @Override
    public String render(DeleteQueryIM query) {
        StringBuilder builder = new StringBuilder();

        builder.append(query.getParent().getSql());

        builder.append("DELETE FROM ");
        builder.append(render(query.getTable()));

        return builder.toString();
    }

    @Override
    public String render(DeleteQueryFinal query) {
        StringBuilder builder = new StringBuilder();

        builder.append(query.getParent().getSql());

        builder.append("\nWHERE ");
        builder.append(render(query.getCondition()));

        return builder.toString();
    }

    @Override
    public String render(InsertQuery query) {
        StringBuilder builder = new StringBuilder();

        prependParentIfExists(builder, query);

        return builder.toString();
    }

    @Override
    public String render(InsertQueryIM query) {
        StringBuilder builder = new StringBuilder();

        builder.append(query.getParent().getSql());

        builder.append("INSERT INTO ");
        builder.append(render(query.getTable()));

        return builder.toString();
    }

    @Override
    public String render(InsertQueryFinal query) {
        StringBuilder builder = new StringBuilder();

        builder.append(query.getParent().getSql());
        builder.append(" (");

        builder.append(
                query.getFields().stream()
                        .map(f -> renderObjectName(f.getName()))
                        .collect(Collectors.joining(", "))
        );

        builder.append(")");

        return builder.toString();
    }

    @Override
    public String render(InsertQueryValues query) {
        StringBuilder builder = new StringBuilder();

        builder.append(query.getParent().getSql());
        builder.append("\nVALUES (");

        builder.append(
                query.getValues().stream()
                        .map(Value::getValue)
                        .collect(Collectors.joining(", "))
        );

        builder.append(")");

        return builder.toString();
    }

    @Override
    public String render(UpdateQuery query) {
        StringBuilder builder = new StringBuilder();

        prependParentIfExists(builder, query);

        builder.append("UPDATE ");
        builder.append(render(query.getTable()));

        return builder.toString();
    }

    @Override
    public String render(UpdateQueryIM query) {
        StringBuilder builder = new StringBuilder();

        IQuery parent = query.getParent();

        builder.append(parent.getSql());

        if (parent instanceof UpdateQueryIM) {
            builder.append(",");
        } else {
            builder.append(" SET");
        }

        builder.append("\n");
        builder.append(renderObjectName(query.getField().getName()));
        builder.append(" = ");
        builder.append(query.getValue().getValue()); // Do not use render(query.getValue()) here, it would render the alias

        return builder.toString();
    }

    @Override
    public String render(UpdateQueryFinal query) {
        StringBuilder builder = new StringBuilder();

        builder.append(query.getParent().getSql());

        builder.append("\nWHERE ");
        builder.append(render(query.getCondition()));

        return builder.toString();
    }

    @Override
    public String render(Field f) {
        StringBuilder builder = new StringBuilder();

        Table table = f.getTable();

        if (table != null) {
            builder.append(render(table));
            builder.append(".");
        }

        builder.append(renderObjectName(f.getName()));

        return builder.toString();
    }

    @Override
    public String render(Table t) {
        StringBuilder builder = new StringBuilder();

        String schema = t.getSchema();
        if (schema != null) {
            builder.append(renderObjectName(schema));
            builder.append(".");
        }

        builder.append(renderObjectName(t.getName()));

        return builder.toString();
    }

    @Override
    public String render(Value v) {
        StringBuilder builder = new StringBuilder();

        builder.append(v.getValue());

        String alias = v.getAlias();
        if (alias != null) {
            builder.append(" AS ");
            builder.append(renderObjectName(alias));
        }

        return builder.toString();
    }

    @Override
    public String render(Source s) {
        StringBuilder builder = new StringBuilder();

        builder.append(s.getValue());

        String alias = s.getAlias();
        if (alias != null) {
            builder.append(" AS ");
            builder.append(renderObjectName(alias));
        }

        return builder.toString();
    }

    @Override
    public String render(Function f) {
        return f.getDefaultString();
    }
}
