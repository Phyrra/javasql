package ch.sama.sql.query.exception;

public class IllegalIdentifierException extends RuntimeException {
    public IllegalIdentifierException() {
        super();
    }

    public IllegalIdentifierException(String message) {
        super(message);
    }

    public IllegalIdentifierException(Throwable cause) {
        super(cause);
    }

    public IllegalIdentifierException(String message, Throwable cause) {
        super(message, cause);
    }
}
