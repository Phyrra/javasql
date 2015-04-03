package ch.sama.sql.csv;

public class CSVWriter {
    private CSVFormat format;

    public CSVWriter() {
        format = new CSVFormat();
    }

    public CSVWriter(CSVFormat format) {
        this.format = format;
    }

    public String write(CSVSet set) {
        StringBuilder builder = new StringBuilder();
        String prefix = "";

        if (set.hasTitle()) {
            builder.append(format.join(set.getTitle()));

            prefix = "\r\n";
        }

        for (CSVRow row : set) {
            builder.append(prefix);
            builder.append(format.join(row));

            prefix = "\r\n";
        }

        return builder.toString();
    }
}
