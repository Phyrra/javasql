package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.query.base.InsertQueryFinal;
import ch.sama.sql.query.base.InsertQueryIM;
import ch.sama.sql.query.helper.Value;

public class TSqlInsertQueryFinal extends InsertQueryFinal {
    TSqlQueryRenderer renderer;

    public TSqlInsertQueryFinal(TSqlQueryRenderer renderer, InsertQueryIM parent, Field[] fields) {
        super(renderer, parent, fields);

        this.renderer = renderer;
    }

    @Override
    public TSqlSelectQuery select(Value... values) {
        return new TSqlSelectQuery(renderer, this, values);
    }

    public TSqlInsertQueryOutput output(Value... values) {
        return new TSqlInsertQueryOutput(renderer, this, values);
    }
}
