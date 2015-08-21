package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.query.base.DeleteQuery;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.InsertQuery;
import ch.sama.sql.query.base.UpdateQuery;
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

    public TSqlSelectQuery select(Value... v) {
        return new TSqlSelectQuery(renderer, this, v);
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
}
