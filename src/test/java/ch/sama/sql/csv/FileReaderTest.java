package ch.sama.sql.csv;

import ch.sama.sql.util.TestFileUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FileReaderTest {
    private static final String PATH = "csv";

    @Test
    public void readFromFile() throws IOException {
        File file = TestFileUtil.getFile(PATH + "/Empty.csv");

        new CSVReader(new CSVFormat()).readFile(file);
    }

    @Test
    public void readFromPath() throws IOException {
        String path = TestFileUtil.getFile(PATH + "/Empty.csv").getPath();

        new CSVReader(new CSVFormat()).readPath(path);
    }

    @Test
    public void readFromResource() throws IOException {
        String path = PATH + "/Empty.csv";

        new CSVReader(new CSVFormat()).readResource(path);
    }

    @Test
    public void noTitle() throws IOException {
        CSVSet set = new CSVReader(new CSVFormat().withTitle(false)).readFile(TestFileUtil.getFile(PATH + "/NoTitle.csv"));

        assertEquals(2, set.size());

        assertEquals(Arrays.asList("1", "Test Street", "23", "Some Place", "Switzerland"), set.get(0).toList());
        assertEquals(Arrays.asList("2", "SomeStreet", "17", "Nowhere", "Turkmenistan"), set.get(1).toList());
    }

    @Test
    public void withTitle() throws IOException {
        CSVSet set = new CSVReader(new CSVFormat().withTitle(true)).readFile(TestFileUtil.getFile(PATH + "/WithTitle.csv"));

        assertEquals(true, set.hasTitle());

        assertEquals(Arrays.asList("ID", "Street", "HouseNumber", "Place", "Country"), set.getTitle().toList());

        assertEquals(2, set.size());

        assertEquals(Arrays.asList("1", "Test Street", "23", "Some Place", "Switzerland"), set.get(0).toList());
        assertEquals(Arrays.asList("2", "SomeStreet", "17", "Nowhere", "Turkmenistan"), set.get(1).toList());
    }
}
