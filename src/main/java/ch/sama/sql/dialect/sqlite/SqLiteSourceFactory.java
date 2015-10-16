package ch.sama.sql.dialect.sqlite;

import ch.sama.sql.query.standard.SourceFactory;

public class SqLiteSourceFactory extends SourceFactory {
    private static final SqLiteQueryRenderer renderer = new SqLiteQueryRenderer();

    public SqLiteSourceFactory() {
        super(renderer);
    }
}