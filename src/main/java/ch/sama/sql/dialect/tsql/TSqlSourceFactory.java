package ch.sama.sql.dialect.tsql;

import ch.sama.sql.query.base.check.Identifier;
import ch.sama.sql.query.exception.IllegalIdentifierException;
import ch.sama.sql.query.helper.Source;
import ch.sama.sql.query.standard.SourceFactory;

public class TSqlSourceFactory extends SourceFactory {
    private static final TSqlQueryRenderer renderer = new TSqlQueryRenderer();

    public TSqlSourceFactory() {
        super (renderer);
    }

    public Source variable(String name) {
        if (!Identifier.test(name)) {
            throw new IllegalIdentifierException(name);
        }

        return new Source(name, "@" + name);
    }
}
