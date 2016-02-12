package ch.sama.sql.dialect.mysql.query;

import ch.sama.sql.dialect.mysql.MySqlQueryRenderer;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.JoinQuery;
import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.helper.condition.ICondition;

public class MySqlJoinQuery extends JoinQuery {
    private MySqlQueryRenderer renderer;

    public MySqlJoinQuery(MySqlQueryRenderer renderer, IQuery parent, Source source) {
        super(renderer, parent, source);

        this.renderer = renderer;
    }

    @Override
    public MySqlJoinQueryFinal on(ICondition c) {
        return new MySqlJoinQueryFinal(renderer, this, c);
    }
}
