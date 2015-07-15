package ch.sama.sql.dbo.result.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class MapResultList implements Iterable<MapResult> {
    private List<MapResult> list;

    MapResultList() {
        list = new ArrayList<MapResult>();
    }

    void add(MapResult result) {
        list.add(result);
    }

    public int size() {
        return list.size();
    }

    public MapResult get(int i) {
        return list.get(i);
    }

    public List<MapResult> toList() {
        return list.stream().collect(Collectors.toList());
    }

    @Override
    public Iterator<MapResult> iterator() {
        return list.iterator();
    }
}
