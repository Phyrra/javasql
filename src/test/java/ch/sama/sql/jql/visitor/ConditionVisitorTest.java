package ch.sama.sql.jql.visitor;

import ch.sama.sql.dialect.tsql.TSqlConditionRenderer;
import ch.sama.sql.dialect.tsql.TSqlValueFactory;
import ch.sama.sql.jql.parser.AntlrJqlConditionBuilder;
import ch.sama.sql.util.GrammarTestBase;
import ch.sama.sql.util.TestFileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ConditionVisitorTest extends GrammarTestBase {
    private static final String PATH = "grammar/jql";
    private static final String ENDING = "jql";

    private static String ONLY_ONE = null;

    private String name;
    private File in;
    private File out;

    private AntlrJqlConditionBuilder builder;

    public ConditionVisitorTest(String name, File in, File out) {
        this.name = name;
        this.in = in;
        this.out = out;

        this.builder = new AntlrJqlConditionBuilder(new TSqlValueFactory());
    }

    @Parameterized.Parameters (name = "{index}: {0}")
    public static Collection tests() throws FileNotFoundException {
        return getTestFiles(PATH, ENDING, ONLY_ONE);
    }

    @Test
    public void testFile() throws IOException {
        String result = builder.build(TestFileUtil.readFile(in)).render(new TSqlConditionRenderer());

        compareResult(in, out, result);
    }
}
