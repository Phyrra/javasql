package ch.sama.sql.dbo;

import java.util.List;

public interface ISchema {
    public List<Table> getTables();

    public Table getTable(String name);

    public boolean hasTable(String name);
}
