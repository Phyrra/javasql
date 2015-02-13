package ch.sama.sql.dbo.result;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultResultSet implements IResultSet {
    private List<ResultRow> rows;

    public DefaultResultSet() {
        rows = new ArrayList<ResultRow>();
    }

    @Override
    public void add(ResultRow row) {
        rows.add(row);
    }

    @Override
    public ResultRow get(int i) {
        return rows.get(i);
    }
    
    @Override
    public int size() {
        return rows.size();
    }
    
    @Override
    public Iterator<ResultRow> iterator() {
        return rows.iterator();
    }
}
