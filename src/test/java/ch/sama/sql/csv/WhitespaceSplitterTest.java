package ch.sama.sql.csv;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class WhitespaceSplitterTest {
    private static final CSVFormat format = new CSVFormat().withFormat(CSVFormat.FORMAT.WHITESPACE);

    /*
        SPLIT
     */

    @Test
    public void oneElement() {
        assertEquals(Arrays.asList("hello"), format.split("hello"));
    }

    @Test
    public void noQuotes() {
        assertEquals(Arrays.asList("hello", "world"), format.split("hello world"));
    }

    @Test
    public void doubleQuotes() {
        assertEquals(Arrays.asList("my darling"), format.split("\"my darling\""));
    }

    @Test
    public void singleQuotes() {
        assertEquals(Arrays.asList("my darling"), format.split("'my darling'"));
    }

    @Test
    public void singleQuoteInDoubleQuotes() {
        assertEquals(Arrays.asList("my 'darling'"), format.split("\"my 'darling'\""));
    }

    @Test
    public void doubleQuoteInSingleQuotes() {
        assertEquals(Arrays.asList("my \"darling\""), format.split("'my \"darling\"'"));
    }

    @Test
    public void escapeDoubleQuote() {
        assertEquals(Arrays.asList("my \"darling\""), format.split("\"my \\\"darling\\\"\""));
    }

    @Test
    public void escapeSingleQuote() {
        assertEquals(Arrays.asList("my 'darling'"), format.split("'my \\'darling\\''"));
    }

    @Test
    public void quotedStart() {
        assertEquals(Arrays.asList("my darling", "hello"), format.split("\"my darling\" hello"));
    }

    @Test
    public void quotedEnd() {
        assertEquals(Arrays.asList("hello", "my darling"), format.split("hello \"my darling\""));
    }

    @Test
    public void quotedMiddle() {
        assertEquals(Arrays.asList("hello", "my darling", "hello"), format.split("hello \"my darling\" hello"));
    }

    @Test
    public void openQuote() {
        assertEquals(Arrays.asList("hello", "my", "darling"), format.split("hello \"my darling"));
    }

    @Test
    public void emptyQuote() {
        assertEquals(Arrays.asList("hello", "", "friend"), format.split("hello \"\" friend"));
    }

    @Test
    public void multiWhitespace() {
        assertEquals(Arrays.asList("hello", "friend"), format.split("hello   friend"));
    }

    /*
        JOIN
     */

    @Test
    public void joinSingle() {
        assertEquals("\"Hello\"", format.join(new CSVRow(Arrays.asList("Hello"))));
    }

    @Test
    public void joinMultiple() {
        assertEquals("\"Hello\" \"Darling\"", format.join(new CSVRow(Arrays.asList("Hello", "Darling"))));
    }

    @Test
    public void withWhitespace() {
        assertEquals("\"Hello\" \"my Darling\"", format.join(new CSVRow(Arrays.asList("Hello", "my Darling"))));
    }

    @Test
    public void withEscape() {
        assertEquals("\"Hello\" \"my \\\"Darling\\\"\"", format.join(new CSVRow(Arrays.asList("Hello", "my \"Darling\""))));
    }
}
