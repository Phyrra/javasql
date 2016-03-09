package ch.sama.sql.csv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class CSVRow implements Iterable<String> {
    private List<String> list;

    public CSVRow() {
        list = new ArrayList<String>();
    }

    public CSVRow(List<String> list) {
        this.list = list;
    }

    public CSVRow(String[] array) {
        list = new ArrayList<String>();
        Collections.addAll(list, array);
    }

    public void add(String s) {
        list.add(s);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public String get(int i) {
        return list.get(i);
    }

    public List<String> toList() {
        return list.stream().collect(Collectors.toList());
    }

    @Override
    public Iterator<String> iterator() {
        return list.iterator();
    }
}
