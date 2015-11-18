package ch.sama.sql.dialect.tsql.grammar.parser;

public class SqlGrammarException extends RuntimeException {
    public SqlGrammarException(String message) {
        super(message);
    }

    public SqlGrammarException(Throwable cause) {
        super(cause);
    }

    public SqlGrammarException(String message, Throwable cause) {
        super(message, cause);
    }
}
