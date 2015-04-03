package ch.sama.sql.mysql.dialect;

import ch.sama.sql.query.base.*;
import ch.sama.sql.query.exception.NotImplementedException;
import ch.sama.sql.query.generic.QueryRenderer;

public class MySqlQueryRenderer extends QueryRenderer {
    public MySqlQueryRenderer() {
        super(new MySqlConditionRenderer(), new MySqlOrderRenderer());
    }

    @Override
    public String render(CTEQuery query) {
        throw new NotImplementedException("MySql does not have CTE (I think?)");
    }

    @Override
    public String render(CTEQueryFinal query) {
        throw new NotImplementedException("MySql does not have CTE (I think?)");
    }

    @Override
    public String renderObjectName(String s) {
        return "`" + s + "`";
    }
}
