package ch.sama.sql.dialect.tsql.grammar.visitor;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class TestBase extends FileHandler {
    public void compare(String expected, String actual) {
        assertEquals(clean(expected), clean(actual));
    }

    public String clean(String s) {
        return s.replace("\r\n", "\n").replace("\t", "    ").trim();
    }

    public String traceToString(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        t.printStackTrace(pw);

        return sw.toString();
    }

}
