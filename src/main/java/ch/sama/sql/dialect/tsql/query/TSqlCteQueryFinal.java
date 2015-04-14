package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.query.base.DeleteQuery;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.InsertQuery;
import ch.sama.sql.query.base.UpdateQuery;
import ch.sama.sql.query.helper.Value;

public class TSqlCteQueryFinal implements IQuery {
    private TSqlQueryRenderer renderer;
    private TSqlCteQuery parent;
    private IQuery query;

    public TSqlCteQueryFinal(TSqlQueryRenderer renderer, TSqlCteQuery parent, IQuery query) {
        this.renderer = renderer;
        this.parent = parent;
        this.query = query;
    }

    @Override
    public IQuery getParent() {
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

    public String getCteName() {
        return parent.getName();
    }

    public IQuery getQuery() {
        return query;
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
