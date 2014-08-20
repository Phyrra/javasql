package ch.sama.sql.query.exception;

public class BadParametersException extends RuntimeException {
    public BadParametersException() {
        super();
    }

    public BadParametersException(String message) {
        super(message);
    }

    public BadParametersException(Throwable cause) {
        super(cause);
    }

    public BadParametersException(String message, Throwable cause) {
        super(message, cause);
    }
}
