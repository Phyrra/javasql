package ch.sama.sql.csv;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class CSVSet implements Iterable<CSVRow> {
    private CSVRow title;
    private List<CSVRow> list;

    public CSVSet() {
        list = new ArrayList<CSVRow>();
    }

    public void addTitle(CSVRow title) {
        this.title = title;
    }

    public void add(CSVRow row) {
        list.add(row);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean hasTitle() {
        return title != null;
    }

    public CSVRow getTitle() {
        return title;
    }

    public CSVRow get(int i) {
        return list.get(i);
    }

    public List<CSVRow> toList() {
        return list.stream().collect(Collectors.toList());
    }

    @Override
    public Iterator<CSVRow> iterator() {
        return list.iterator();
    }
}
