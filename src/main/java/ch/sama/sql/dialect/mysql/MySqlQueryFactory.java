package ch.sama.sql.dialect.mysql;

import ch.sama.sql.dialect.mysql.query.MySqlLimitQuery;
import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.dialect.tsql.query.TSqlCteQuery;
import ch.sama.sql.dialect.tsql.query.TSqlQuery;
import ch.sama.sql.dialect.tsql.query.TSqlSelectQuery;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.standard.QueryFactory;

public class MySqlQueryFactory extends QueryFactory {
    private static final MySqlQueryRenderer renderer = new MySqlQueryRenderer();

    @Override
    public MySqlQueryRenderer renderer() {
        return renderer;
    }

    public MySqlLimitQuery limit(IQuery parent, int start, int stop) {
        return new MySqlLimitQuery(this, parent, start, stop);
    }

    // TODO: Overwrite queries that can have limit:
    //  - from
    //  - join final
    //  - where
    //  - order
}
