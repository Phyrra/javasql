package ch.sama.sql.jql.parser;

public class JqlGrammarException extends RuntimeException {
    public JqlGrammarException(String message) {
        super(message);
    }

    public JqlGrammarException(Throwable cause) {
        super(cause);
    }

    public JqlGrammarException(String message, Throwable cause) {
        super(message, cause);
    }
}
