package ch.sama.sql.dialect.tsql.schema;

import ch.sama.sql.jpa.Column;
import ch.sama.sql.jpa.Entity;

@Entity(name = "Columns")
public class DbColumn {
    @Column(name = "COLUMN_NAME")
    private String name;

    @Column(name = "DATA_TYPE")
    private String type;

    @Column(name = "CHARACTER_MAXIMUM_LENGTH", nullable = true)
    private Integer charMaxLength;

    @Column(name = "IS_NULLABLE")
    private Boolean isNullable;

    @Column(name = "COLUMN_DEFAULT", nullable = true)
    private String defaultValue;

    @Column(name = "IS_PKEY")
    private Boolean isPrimaryKey;

    @Column(name = "IS_AUTO_INCREMENT")
    private Boolean isAutoIncrement;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Integer getCharMaxLength() {
        return charMaxLength;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public boolean isAutoIncrement() {
        return isAutoIncrement;
    }
}
