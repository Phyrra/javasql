package ch.sama.sql.dialect.mysql.query;

import ch.sama.sql.dialect.mysql.MySqlQueryRenderer;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.SelectQuery;
import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.condition.ICondition;

public class MySqlSelectQuery extends SelectQuery {
    private MySqlQueryRenderer renderer;

    public MySqlSelectQuery(MySqlQueryRenderer renderer, IQuery parent, Value[] v) {
        super(renderer, parent, v);

        this.renderer = renderer;
    }

    @Override
    public MySqlFromQuery from(Source... s) {
        return new MySqlFromQuery(renderer, this, s);
    }

    @Override
    public MySqlQuery union() {
        return new MySqlQuery(renderer, this);
    }

    @Override
    public MySqlWhereQuery where(ICondition c) {
        return new MySqlWhereQuery(renderer, this, c);
    }
}
