package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.query.helper.Value;

import java.util.Arrays;
import java.util.List;

public class TSqlInsertQueryOutput implements IQuery {
    private TSqlQueryRenderer renderer;
    private TSqlInsertQueryFinal parent;
    private List<Value> values;

    private String destination;

    public TSqlInsertQueryOutput(TSqlQueryRenderer renderer, TSqlInsertQueryFinal parent, Value[] values) {
        this.renderer = renderer;
        this.parent = parent;
        this.values = Arrays.asList(values);
    }

    public List<Value> getValues() {
        return values;
    }

    public boolean hasDestination() {
        return destination != null;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public TSqlInsertQueryFinal getParent() {
        return parent;
    }

    @Override
    public String getSql() {
        return renderer.render(this);
    }

    @Override
    public IQuery chainTo(IQuery query) {
        return this.parent.chainTo(query);
    }

    public TSqlInsertQueryOutput into(String destination) {
        if (!destination.matches("^(\\[\\w+\\]|@?\\w+)$")) {
            throw new IllegalIdentifierException(destination);
        }

        this.destination = destination;

        return this;
    }

    public TSqlSelectQuery select(Value... values) {
        return new TSqlSelectQuery(renderer, this, values);
    }
}
