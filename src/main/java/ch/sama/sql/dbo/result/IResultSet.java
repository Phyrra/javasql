package ch.sama.sql.dbo.result;

import java.util.Iterator;

public interface IResultSet extends Iterable<ResultRow> {
    public void add(ResultRow row);
    public ResultRow get(int i);
    public int size();
    public Iterator<ResultRow> iterator();
}
