package ch.sama.sql.dbo.schema;

import ch.sama.sql.jpa.Column;
import ch.sama.sql.jpa.Entity;

@Entity(name = "table")
public class DbTable {
    @Column(name = "TABLE_SCHEMA")
    private String schema;

    @Column(name = "TABLE_NAME")
    private String name;

    public String getSchema() {
        return schema;
    }

    public String getName() {
        return name;
    }
}
