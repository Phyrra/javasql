package ch.sama.sql.dialect.tsql.grammar.visitor;

import ch.sama.sql.dialect.tsql.grammar.runner.AntlrQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

@RunWith(Parameterized.class)
public class QueryVisitorTest extends TestBase {
    private static final String PATH = "grammar/query";

    private static final String ENDING = "sql";
    private static final String OUT = "out";
    private static final String GEN = "gen";

    private static String ONLY_ONE = null;

    private String name;
    private File in;
    private File out;

    private AntlrQueryBuilder builder;

    public QueryVisitorTest(String name, File in, File out) {
        this.name = name;
        this.in = in;
        this.out = out;

        this.builder = new AntlrQueryBuilder();
    }

    @Parameterized.Parameters (name = "{index}: {0}")
    public static Collection tests() throws FileNotFoundException {
        return getTestFiles(PATH, ONLY_ONE);
    }

    @Test
    public void testFile() throws IOException {
        String result = builder.build(TestUtil.readFile(in)).getSql();

        compareResult(in, out, result);
    }
}
