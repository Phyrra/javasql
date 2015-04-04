package ch.sama.sql.dialect.tsql;

import ch.sama.sql.query.standard.SourceFactory;

public class TSqlSourceFactory extends SourceFactory {
    private static final TSqlQueryRenderer renderer = new TSqlQueryRenderer();

    public TSqlSourceFactory() {
        super (renderer);
    }
}
