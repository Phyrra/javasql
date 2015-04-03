package ch.sama.sql.mysql.dialect;

import ch.sama.sql.query.generic.SourceFactory;

public class MySqlSourceFactory extends SourceFactory {
    public MySqlSourceFactory() {
        super(new MySqlQueryRenderer());
    }
}
