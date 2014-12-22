package ch.sama.sql.grammar.visitor;

import static org.junit.Assert.assertEquals;

public class TestBase extends FileHandler {
    public void compare(String expected, String actual) {
        assertEquals(clean(expected), clean(actual));
    }

    public String clean(String s) {
        return s.replace("\r\n", "\n").replace("\t", "    ").trim();
    }
}
