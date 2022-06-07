package app.domain.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class CsvExporter {
  private String filePath;

  public CsvExporter(String filePath) {
    this.filePath = filePath;
  }

  private BufferedWriter bf;

  public void writeToFile(Map<Object, Object> content) {
    bf = null;
    try {
      File file = new File(this.filePath);
      file.createNewFile();

      bf = new BufferedWriter(new FileWriter(file));

      bf.write("Date;NumberOfFullyVaccinatedUsersPerDay");
      bf.newLine();
      for (Map.Entry<Object, Object> entry : content.entrySet()) {

        bf.write(entry.getKey() + ";" + entry.getValue());
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
