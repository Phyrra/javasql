package ch.sama.sql.grammar.visitor.print;

import ch.sama.sql.grammar.runner.AntlrSqlPrinter;
import ch.sama.sql.grammar.visitor.TestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class TestRunner extends TestBase {
    private static final String PATH = "src/test/java/ch/sama/sql/grammar/visitor/print";

    private static final String ENDING = "sql";
    private static final String OUT = "out";
    private static final String GEN = "gen";

    private static String ONLY_ONE = null;

    private File in;
    private File out;

    public TestRunner(File in, File out) {
        this.in = in;
        this.out = out;
    }

    @Parameterized.Parameters
    public static Collection tests() {
        List<Object[]> files = new ArrayList<Object[]>();

        if (ONLY_ONE == null) {
            File dir = new File(PATH);
            for (File file : dir.listFiles()) {
                if (file.getName().endsWith("." + ENDING)) {
                    files.add(new Object[]{
                            file,
                            new File(PATH + "/" + file.getName() + "." + OUT)
                    });
                }
            }
        } else {
            files.add(new Object[] {
                    new File(PATH + "/" + ONLY_ONE),
                    new File(PATH + "/" + ONLY_ONE + "." + OUT)
            });
        }

        return files;
    }

    @Test
    public void testFile() {
        String result;

        try {
            AntlrSqlPrinter printer = new AntlrSqlPrinter();

            result = printer.run(readFile(in));
        }  catch (Exception e) {
            result = e.getMessage();
        }

        try {
            if (out.exists()) {
                compare(readFile(out), result);
            } else {
                writeFile(new File(PATH + "/" + in.getName() + "." + GEN), result);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
