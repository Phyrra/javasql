package ch.sama.sql.dialect.tsql.grammar.visitor;

import ch.sama.sql.dialect.tsql.grammar.runner.AntlrSqlPrinter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

@RunWith(Parameterized.class)
public class PrintVisitorTest extends TestBase {
    private static final String PATH = "grammar/print";

    private static String ONLY_ONE = null;

    private String name;
    private File in;
    private File out;

    private AntlrSqlPrinter printer;

    public PrintVisitorTest(String name, File in, File out) {
        this.name = name;
        this.in = in;
        this.out = out;

        this.printer = new AntlrSqlPrinter();
    }

    @Parameterized.Parameters (name = "{index}: {0}")
    public static Collection tests() throws FileNotFoundException {
        return getTestFiles(PATH, ONLY_ONE);
    }

    @Test
    public void testFile() throws IOException {
        String result = printer.print(TestUtil.readFile(in));

        compareResult(in, out, result);
    }
}
