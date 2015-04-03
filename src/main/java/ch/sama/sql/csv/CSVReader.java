package ch.sama.sql.csv;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class CSVReader {
    private CSVFormat format;

    public CSVReader() {
        this.format = new CSVFormat();
    }

    public CSVReader(CSVFormat format) {
        this.format = format;
    }

    public CSVSet readPath(String file) throws IOException {
        return readStream(new FileInputStream(new File(file)));
    }

    public CSVSet readResource(String file) throws IOException {
        // For whatever reason (IDE?) getResourceAsStream always returns null..

        URL resource = this.getClass().getClassLoader().getResource(file);
        if (resource == null) {
            throw new FileNotFoundException();
        }

        URLConnection urlConnection = resource.openConnection();
        urlConnection.setUseCaches(false); // ..unless I set this

        return readStream(urlConnection.getInputStream());
    }

    public CSVSet readFile(File file) throws IOException {
        return readStream(new FileInputStream(file));
    }

    public CSVSet readStream(InputStream stream) throws IOException {
        CSVSet set = new CSVSet();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(stream));

            String line;
            while ((line = br.readLine()) != null) {
                CSVRow row = new CSVRow(format.split(line));

                if (format.isWithTitle() && !set.hasTitle()) {
                    set.addTitle(row);
                } else {
                    set.add(row);
                }
            }
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // Ignore
                }
            }
        }

        return set;
    }
}
