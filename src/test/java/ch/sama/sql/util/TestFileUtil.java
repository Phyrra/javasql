package ch.sama.sql.util;

import java.io.*;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class TestFileUtil {
    public static File getFile(String path) throws FileNotFoundException {
        URL url = TestFileUtil.class.getClassLoader().getResource(path);
        if (url == null) {
            throw new FileNotFoundException("File " + path + " not found");
        }

        return new File(url.getPath());
    }

    public static String readFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            if (builder.length() > 0) {
                builder.append("\r\n");
            }

            builder.append(line);
        }
        br.close();

        return builder.toString();
    }

    public static void writeFile(File file, String s) throws IOException {
        // A bit of a hack:
        //  The files should be created in the real resources directory, not the build directory
        String fixedPath = file.getAbsolutePath().replace("\\build\\resources\\test\\", "\\src\\test\\resources\\");
        File fixedFile = new File(fixedPath);

        BufferedWriter bw = new BufferedWriter(new FileWriter(fixedFile));
        bw.write(s);
        bw.flush();
        bw.close();
    }

    public static void compare(String expected, String actual) {
        assertEquals(clean(expected), clean(actual));
    }

    public static String clean(String s) {
        return s.replace("\r\n", "\n").replace("\t", "    ").trim();
    }

    public static String traceToString(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        t.printStackTrace(pw);

        return sw.toString();
    }
}
