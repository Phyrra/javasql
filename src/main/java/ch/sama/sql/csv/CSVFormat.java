package ch.sama.sql.csv;

import java.util.List;

public class CSVFormat {
    public static enum FORMAT {
        WHITESPACE(new WhiteSpaceSplitter());

        private ISplitter splitter;

        FORMAT(ISplitter splitter) {
            this.splitter = splitter;
        }

        public ISplitter getSplitter() {
            return splitter;
        }
    }

    private boolean withTitle;
    private FORMAT format;

    public CSVFormat() {
        withTitle = false;
        format = FORMAT.WHITESPACE;
    }

    public CSVFormat withTitle(boolean title) {
        this.withTitle = title;

        return this;
    }

    public CSVFormat withFormat(FORMAT format) {
        this.format = format;

        return this;
    }

    public boolean isWithTitle() {
        return withTitle;
    }

    public FORMAT getFormat() {
        return format;
    }

    public List<String> split(String s) {
        return format.getSplitter().split(s);
    }

    public String join(CSVRow row) {
        return format.getSplitter().join(row.toList());
    }
}
