package ch.sama.sql.tsql.dialect;

import ch.sama.sql.query.generic.SourceFactory;

class TSqlSourceFactory extends SourceFactory {
    public TSqlSourceFactory() {
        super(new TSqlQueryRenderer());
    }
}
