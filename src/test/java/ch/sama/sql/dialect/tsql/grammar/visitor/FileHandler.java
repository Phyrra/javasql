package ch.sama.sql.dialect.tsql.grammar.visitor;

import java.io.*;

public class FileHandler {
    public String readFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();

        String prefix = "";
        String line;
        while ((line = br.readLine()) != null) {
            builder.append(prefix);
            builder.append(line);

            prefix = "\r\n";
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
