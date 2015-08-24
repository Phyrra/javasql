package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.query.base.*;
import ch.sama.sql.query.helper.Value;

abstract class MainQuery implements IQuery {
    private TSqlQueryRenderer renderer;

    MainQuery(TSqlQueryRenderer renderer) {
        this.renderer = renderer;
    }

    TSqlQueryRenderer getRenderer() {
        return renderer;
    }

    public TSqlCteQuery with(String name) {
        return new TSqlCteQuery(renderer, this, name);
    }

    public TSqlSelectQuery select(Value... values) {
        return new TSqlSelectQuery(renderer, this, values);
    }

    public InsertQuery insert() {
        return new InsertQuery(renderer, this);
    }

    public DeleteQuery delete() {
        return new DeleteQuery(renderer, this);
    }

    public UpdateQuery update(Table table) {
        return new UpdateQuery(renderer, this, table);
    }

    public UpdateQuery update(String table) {
        return update(new Table(table));
    }

    public UnionQuery union(IQuery... queries) {
        return new UnionQuery(renderer, this, queries);
    }
}
