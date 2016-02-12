package ch.sama.sql.dialect.mysql;

import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.query.standard.SourceFactory;

public class MySqlSourceFactory extends SourceFactory {
    private static final MySqlQueryRenderer renderer = new MySqlQueryRenderer();

    public MySqlSourceFactory() {
        super (renderer);
    }
}
