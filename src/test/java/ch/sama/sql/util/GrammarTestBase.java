package ch.sama.sql.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GrammarTestBase {
    private static final String OUT = "out";
    private static final String GEN = "gen";

    public static Collection getTestFiles(String directory, String ending, String onlyOne) throws FileNotFoundException {
        List<Object[]> files = new ArrayList<Object[]>();

        File dir = TestFileUtil.getFile(directory);

        if (onlyOne == null) {
            for (File file : dir.listFiles()) {
                String fileName = file.getName();

                if (fileName.endsWith("." + ending)) {
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
                TestFileUtil.compare(TestFileUtil.readFile(out), result);
            } else {
                TestFileUtil.writeFile(new File(in.getParentFile(), in.getName() + "." + GEN), result);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
