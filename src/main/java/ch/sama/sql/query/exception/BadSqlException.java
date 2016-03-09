package ch.sama.sql.query.exception;

public class BadSqlException extends RuntimeException {
    public BadSqlException() {
        super();
    }

    public BadSqlException(String message) {
        super(message);
    }

    public BadSqlException(Throwable cause) {
        super(cause);
    }

    public BadSqlException(String message, Throwable cause) {
        super(message, cause);
    }
}
