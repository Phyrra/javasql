package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.InsertQuery;

public class TSqlInsertQuery extends InsertQuery {
    private TSqlQueryRenderer renderer;

    public TSqlInsertQuery(TSqlQueryRenderer renderer, IQuery parent) {
        super(renderer, parent);

        this.renderer = renderer;
    }

    @Override
    public TSqlInsertQueryIM into(Table table) {
        return new TSqlInsertQueryIM(renderer, this, table);
    }

    @Override
    public TSqlInsertQueryIM into(String table) {
        return into(new Table(table));
    }
}
