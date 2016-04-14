package ch.sama.sql.dialect.mysql;

import ch.sama.sql.query.standard.ValueFactory;

public class MySqlValueFactory extends ValueFactory {
    private static final MySqlQueryRenderer renderer = new MySqlQueryRenderer();

    public MySqlValueFactory() {
        super(renderer);
    }
}
