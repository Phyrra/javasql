package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.base.*;
import ch.sama.sql.query.generic.QueryRenderer;

class TSqlQueryRenderer extends QueryRenderer {
    public TSqlQueryRenderer() {
        super(new TSqlConditionRenderer(), new TSqlOrderRenderer());
    }

    @Override
    public String renderObjectName(String s) {
        return "[" + s + "]";
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

        builder.append(renderObjectName(query.getName()));

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
}