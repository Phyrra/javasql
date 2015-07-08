package ch.sama.sql.dialect.tsql.query;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.query.base.InsertQuery;
import ch.sama.sql.query.base.InsertQueryFinal;
import ch.sama.sql.query.base.InsertQueryIM;

public class TSqlInsertQueryIM extends InsertQueryIM {
    private TSqlQueryRenderer renderer;

    public TSqlInsertQueryIM(TSqlQueryRenderer renderer, InsertQuery parent, Table table) {
        super(renderer, parent, table);

        this.renderer = renderer;
    }

    @Override
    public TSqlInsertQueryFinal columns(Field... fields) {
        return new TSqlInsertQueryFinal(renderer, this, fields);
    }

    @Override
    public TSqlInsertQueryFinal columns(String... fields) {
        Field[] fieldList = new Field[fields.length];
        for (int i = 0; i < fieldList.length; ++i) {
            fieldList[i] = new Field(fields[i]);
        }

        return columns(fieldList);
    }
}
