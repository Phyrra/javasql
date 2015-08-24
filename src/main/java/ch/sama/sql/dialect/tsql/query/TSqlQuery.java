package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dbo.IType;
import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.Query;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.ICondition;

public class TSqlQuery extends Query {
    private TSqlQueryRenderer renderer;

    public TSqlQuery(TSqlQueryRenderer renderer) {
        super(renderer);

        this.renderer = renderer;
    }

    public TSqlQuery(TSqlQueryRenderer renderer, IQuery parent) {
        super(renderer, parent);

        this.renderer = renderer;
    }

    @Override
    public TSqlSelectQuery select(Value... values) {
        return new TSqlSelectQuery(renderer, this, values);
    }

    @Override
    public TSqlInsertQuery insert() {
        return new TSqlInsertQuery(renderer, this);
    }

    public TSqlCteQuery with(String alias) {
        return new TSqlCteQuery(renderer, this, alias);
    }

    public TSqlIfQuery doif(ICondition condition, IQuery query) {
        return new TSqlIfQuery(renderer, this, condition, query);
    }

    public TSqlDeclareQuery declare(String variable, IType type, Value value) {
        return new TSqlDeclareQuery(renderer, this, variable, type, value);
    }
}
