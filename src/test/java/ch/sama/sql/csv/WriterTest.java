package ch.sama.sql.csv;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class WriterTest {
    private static final CSVWriter writer = new CSVWriter(new CSVFormat().withFormat(CSVFormat.FORMAT.WHITESPACE));

    @Test
    public void noTitle() {
        CSVSet set = new CSVSet();
        set.add(new CSVRow(Arrays.asList("1", "Some Street", "23", "Usbekistan")));
        set.add(new CSVRow(Arrays.asList("2", "Other Street", "17", "Aserbeitschan")));

        assertEquals(
                "\"1\" \"Some Street\" \"23\" \"Usbekistan\"\r\n" +
                "\"2\" \"Other Street\" \"17\" \"Aserbeitschan\"",
                writer.write(set)
        );
    }

    @Test
    public void withTitle() {
        CSVSet set = new CSVSet();
        set.addTitle(new CSVRow(Arrays.asList("ID", "Street", "HouseNumber", "Country")));
        set.add(new CSVRow(Arrays.asList("1", "Some Street", "23", "Usbekistan")));

        assertEquals(
                "\"ID\" \"Street\" \"HouseNumber\" \"Country\"\r\n" +
                "\"1\" \"Some Street\" \"23\" \"Usbekistan\"",
                writer.write(set)
        );
    }
}
