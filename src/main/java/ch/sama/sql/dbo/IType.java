package ch.sama.sql.dbo;

/**
 * Database Type
 */
public interface IType {
    /**
     * Get the string representation of the type
     * as used by the database
     * @return string of type
     */
    public String getString();
}
