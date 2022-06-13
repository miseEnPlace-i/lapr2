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

  public String toExportFileString(Map<Calendar, Integer> data, String header, String separator) {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    StringBuilder sb = new StringBuilder();

    sb.append(header);
    for (Map.Entry<Calendar, Integer> entry : data.entrySet()) {
      sb.append(format.format(entry.getKey().getTime()) + separator + entry.getValue());
      sb.append("\n");
    }
    return sb.toString();
  }
}
