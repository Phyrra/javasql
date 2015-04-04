package ch.sama.sql.dialect.mysql;

import ch.sama.sql.dialect.mysql.query.*;
import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.dialect.tsql.query.TSqlCteQuery;
import ch.sama.sql.dialect.tsql.query.TSqlQuery;
import ch.sama.sql.dialect.tsql.query.TSqlSelectQuery;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.JoinQuery;
import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.ICondition;
import ch.sama.sql.query.helper.order.IOrder;
import ch.sama.sql.query.standard.QueryFactory;

public class MySqlQueryFactory extends QueryFactory {
    private static final MySqlQueryRenderer renderer = new MySqlQueryRenderer();

    @Override
    public MySqlQueryRenderer renderer() {
        return renderer;
    }

    @Override
    public MySqlQuery query() {
        return new MySqlQuery(this);
    }

    @Override
    public MySqlQuery query(IQuery parent) {
        return new MySqlQuery(this, parent);
    }

    @Override
    public MySqlSelectQuery select(IQuery parent, Value[] v) {
        return new MySqlSelectQuery(this, parent, v);
    }

    @Override
    public MySqlFromQuery from(IQuery parent, Source[] s) {
        return new MySqlFromQuery(this, parent, s);
    }

    @Override
    public MySqlWhereQuery where(IQuery parent, ICondition c) {
        return new MySqlWhereQuery(this, parent, c);
    }

    @Override
    public MySqlOrderQuery order(IQuery parent, IOrder[] o) {
        return new MySqlOrderQuery(this, parent, o);
    }

    @Override
    public MySqlJoinQuery join(IQuery parent, Source s) {
        return new MySqlJoinQuery(this, parent, s);
    }

    @Override
    public MySqlJoinQueryFinal joinFinal(JoinQuery parent, ICondition c) {
        return new MySqlJoinQueryFinal(this, parent, c);
    }

    public MySqlLimitQuery limit(IQuery parent, int start, int stop) {
        return new MySqlLimitQuery(this, parent, start, stop);
    }
}
