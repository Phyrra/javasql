package ch.sama.sql.dialect.tsql.grammar.visitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestBase {
    private static final String ENDING = "sql";
    private static final String OUT = "out";
    private static final String GEN = "gen";

    public static Collection getTestFiles(String directory, String onlyOne) throws FileNotFoundException {
        List<Object[]> files = new ArrayList<Object[]>();

        File dir = TestUtil.getFile(directory);

        if (onlyOne == null) {
            for (File file : dir.listFiles()) {
                String fileName = file.getName();

                if (fileName.endsWith("." + ENDING)) {
                    files.add(new Object[]{
                            fileName,
                            file,
                            new File(dir, fileName + "." + OUT)
                    });
                }
            }
        } else {
            files.add(new Object[] {
                    onlyOne,
                    new File(dir, onlyOne),
                    new File(dir, onlyOne + "." + OUT)
            });
        }

        return files;
    }

    public void compareResult(File in, File out, String result) {
        try {
            if (out.exists()) {
                TestUtil.compare(TestUtil.readFile(out), result);
            } else {
                TestUtil.writeFile(new File(in.getParentFile(), in.getName() + "." + GEN), result);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
