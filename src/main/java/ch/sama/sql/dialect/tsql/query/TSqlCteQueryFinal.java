package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.DeleteQuery;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.InsertQuery;
import ch.sama.sql.query.base.UpdateQuery;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.dialect.tsql.TSqlQueryFactory;

public class TSqlCteQueryFinal implements IQuery {
    private TSqlQueryFactory factory;
    private TSqlCteQuery parent;
    private IQuery query;

    public TSqlCteQueryFinal(TSqlQueryFactory factory, TSqlCteQuery parent, IQuery query) {
        this.factory = factory;
        this.parent = parent;
        this.query = query;
    }

    @Override
    public IQuery getParent() {
        return parent;
    }

    @Override
    public String getSql() {
        return factory.renderer().render(this);
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
        return factory.with(this, name);
    }

    public TSqlSelectQuery select(Value... v) {
        return factory.select(this, v);
    }

    public InsertQuery insert() {
        return factory.insert(this);
    }

    public DeleteQuery delete() {
        return factory.delete(this);
    }

    public UpdateQuery update(Table table) {
        return factory.update(this, table);
    }

    public UpdateQuery update(String table) {
        return update(new Table(table));
    }
}
