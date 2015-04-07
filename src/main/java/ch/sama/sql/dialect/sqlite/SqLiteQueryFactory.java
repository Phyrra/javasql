package ch.sama.sql.dialect.sqlite;

import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.standard.QueryFactory;

public class SqLiteQueryFactory extends QueryFactory {
    private static final SqLiteQueryRenderer renderer = new SqLiteQueryRenderer();

    @Override
    public IQueryRenderer renderer() {
        return renderer;
    }
}