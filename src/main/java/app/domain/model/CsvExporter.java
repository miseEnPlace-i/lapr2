package app.domain.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

public class CsvExporter {
  private String filePath;

  public CsvExporter(String filePath) {
    this.filePath = filePath;
  }

  private BufferedWriter bf;

  public boolean writeToFile(Map<Calendar, Integer> content) {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    bf = null;
    try {
      File file = new File(this.filePath + "Data.csv");
      file.createNewFile();

      bf = new BufferedWriter(new FileWriter(file));

      bf.write("Date;NumberOfFullyVaccinatedUsersPerDay");
      bf.newLine();
      for (Map.Entry<Calendar, Integer> entry : content.entrySet()) {

        bf.write(format.format(entry.getKey().getTime()) + ";" + entry.getValue());
        bf.newLine();
      }
      bf.flush();
      return true;
    } catch (IOException e) {
      System.err.println(e);
      e.printStackTrace();
      return false;

    }
    finally {
      try {
        bf.close();
      } catch (Exception e) {
      }
    }
  }
}
