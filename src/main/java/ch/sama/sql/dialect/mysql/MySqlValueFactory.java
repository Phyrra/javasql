package ch.sama.sql.dialect.mysql;

import ch.sama.sql.dbo.Table;
import ch.sama.sql.dialect.tsql.TSqlQueryRenderer;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.standard.ValueFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MySqlValueFactory extends ValueFactory {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final MySqlQueryRenderer renderer = new MySqlQueryRenderer();

    public MySqlValueFactory() {
        super(renderer);
    }

    public Value table(String table) {
        return table(new Table(table));
    }

    public Value table(Table table) {
        return new Value(table, renderer.render(table) + ".*");
    }
}
