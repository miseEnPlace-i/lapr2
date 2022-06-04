package app.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class CsvExporter {

    private BufferedWriter bf;

    public void writeToFile(String filename, Map<Object, Object> content) {
        bf = null;
        try {
            File file = new File(filename);
            file.createNewFile();
            bf = new BufferedWriter(new FileWriter(file));

            for (Map.Entry<Object, Object> entry : content.entrySet()) {

                bf.write(entry.getKey() + ":" + entry.getValue());
                bf.newLine();
            }
            bf.flush();

        } catch (IOException e) {
            System.err.println(e);
            e.printStackTrace();

        }
        finally {
            try {
                bf.close();
            } catch (Exception e) {
            }
        }
    }
}
