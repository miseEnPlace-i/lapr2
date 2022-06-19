package app.domain.model;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import app.service.FileUtils;
import app.service.readCSV.ICSVReader;

/**
 * CsvReader
 * 
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */
public class CSVReader {
  private String path;

  /**
   * CsvReader Constructor
   * 
   * @param path
   */
  public CSVReader(String path) {
    if (path == null || path == "") throw new IllegalArgumentException("File path cannot be null or empty!");

    this.path = path;
  }

  /**
   * @return a list of strings representing the data in the file
   * @throws ClassNotFoundException if is not possible to convert the data to class
   * @throws InstantiationException if is not possible to instantiate the class
   * @throws IllegalAccessException if is not possible to access the class
   * @throws FileNotFoundException if is not possible to find the file at the given path
   * @throws NoSuchMethodException if is not possible to find the method
   * @throws InvocationTargetException if is not possible to invoke the method
   */
  public List<String[]> read()
      throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, NoSuchMethodException, InvocationTargetException {
    List<String> fileData = FileUtils.readFromFile(this.path);

    String className = fileData.get(0).contains(",") ? "app.service.readCSV.MissingHeaderCSVReader" : "app.service.readCSV.HeaderCSVReader";

    Class<?> oClass = Class.forName(className);

    ICSVReader reader = (ICSVReader) oClass.getDeclaredConstructor().newInstance();

    List<String[]> userDataList = reader.read(fileData);

    return userDataList;
  }
}
