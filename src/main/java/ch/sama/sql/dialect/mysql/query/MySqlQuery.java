package ch.sama.sql.dialect.mysql.query;

import ch.sama.sql.dialect.mysql.MySqlQueryRenderer;
import ch.sama.sql.query.base.IQuery;
import ch.sama.sql.query.base.Query;
import ch.sama.sql.query.helper.Value;

public class MySqlQuery extends Query {
    private MySqlQueryRenderer renderer;

    public MySqlQuery(MySqlQueryRenderer renderer) {
        super(renderer);

        this.renderer = renderer;
    }

    public MySqlQuery(MySqlQueryRenderer renderer, IQuery parent) {
        super(renderer, parent);

        this.renderer = renderer;
    }

    @Override
    public MySqlSelectQuery select(Value... v) {
        return new MySqlSelectQuery(renderer, this, v);
    }
}
