package ch.sama.sql.dialect.tsql.grammar.visitor;

import java.io.*;

public class FileHandler {
    public String readFile(File file) throws IOException {
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

    public void writeFile(File file, String s) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(s);
        bw.flush();
        bw.close();
    }
}
