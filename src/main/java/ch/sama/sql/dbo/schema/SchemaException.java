package ch.sama.sql.dbo.schema;

public class SchemaException extends RuntimeException {
    public SchemaException() {
        super();
    }

    public SchemaException(String message) {
        super(message);
    }

    public SchemaException(Throwable cause) {
        super(cause);
    }

    public SchemaException(String message, Throwable cause) {
        super(message, cause);
    }
}
