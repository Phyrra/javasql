package ch.sama.sql.query.exception;

public class BadParameterException extends RuntimeException {
    public BadParameterException() {
        super();
    }

    public BadParameterException(String message) {
        super(message);
    }

    public BadParameterException(Throwable cause) {
        super(cause);
    }

    public BadParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
