package ch.sama.sql.csv;

import java.util.List;

interface ISplitter {
    public List<String> split(String s);
    public String join(List<String> list);
}
