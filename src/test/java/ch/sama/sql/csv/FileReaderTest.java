package ch.sama.sql.csv;

import ch.sama.sql.csv.CSVFormat;
import ch.sama.sql.csv.CSVReader;
import ch.sama.sql.csv.CSVSet;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FileReaderTest {
    private static final String PATH = "src/test/java/ch/sama/sql/csv/resources";

    @Test
    public void noTitle() throws IOException {
        CSVSet set = new CSVReader(new CSVFormat().withTitle(false)).readPath(PATH + "/NoTitle.csv");

        assertEquals(2, set.size());

        assertEquals(Arrays.asList("1", "Test Street", "23", "Some Place", "Switzerland"), set.get(0).toList());
        assertEquals(Arrays.asList("2", "SomeStreet", "17", "Nowhere", "Turkmenistan"), set.get(1).toList());
    }

    @Test
    public void withTitle() throws IOException {
        CSVSet set = new CSVReader(new CSVFormat().withTitle(true)).readPath(PATH + "/WithTitle.csv");

        assertEquals(true, set.hasTitle());

        assertEquals(Arrays.asList("ID", "Street", "HouseNumber", "Place", "Country"), set.getTitle().toList());

        assertEquals(2, set.size());

        assertEquals(Arrays.asList("1", "Test Street", "23", "Some Place", "Switzerland"), set.get(0).toList());
        assertEquals(Arrays.asList("2", "SomeStreet", "17", "Nowhere", "Turkmenistan"), set.get(1).toList());
    }
}
