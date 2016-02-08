package ch.sama.sql.query.base;

import org.antlr.v4.runtime.misc.NotNull;

/**
 * A single part of an SQL query
 */
public interface IQuery {
    /**
     * Get the query of this part
     * inverse linked list
     * @return immediate parent
     */
	public IQuery getParent();

    /**
     * Get SQL query string for this part and all its parents
     * @return SQL query string
     */
    public String getSql();

    /**
     * Create a chain with the argument query
     * set it as parent of this part and return the argument
     * @param query the query to be set as parent
     * @return query of the argument
     */
    public IQuery chainTo(@NotNull IQuery query);
}