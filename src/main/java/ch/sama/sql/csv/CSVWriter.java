package ch.sama.sql.csv;

import java.util.stream.Collectors;

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

        if (set.hasTitle()) {
            builder.append(format.join(set.getTitle()));
            builder.append("\r\n");
        }

        builder.append(
                set.toList().stream()
                        .map(format::join)
                        .collect(Collectors.joining("\r\n"))
        );

        return builder.toString();
    }
}
