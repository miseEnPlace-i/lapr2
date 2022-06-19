package app.controller;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;
import app.domain.model.CSVReader;
import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.model.store.SNSUserStore;

/**
 * UploadUsersFromFileController Controller
 * 
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */
public class UploadUsersFromFileController {
  private Company company;
  private CSVReader csvReader;

  public UploadUsersFromFileController(Company company) {
    this.company = company;
    this.csvReader = null;
  }

  /**
   * @param filePathName the path of the file to be exported
   * @return
   */
  public CSVReader createCsvReader(String filePathName) {
    this.csvReader = new CSVReader(filePathName);
    return csvReader;
  }

  /**
   * 
   * @return the list of read users
   * @throws ParseException if the data is not valid and is not possible to parse it to the specified format
   * @throws ClassNotFoundException if is not possible to convert the data to class
   * @throws InstantiationException if is not possible to create an instance of the class
   * @throws IllegalAccessException if is not possible to access the class
   * @throws FileNotFoundException if the filepath is not valid
   * @throws NoSuchMethodException if is not possible to access the method
   * @throws InvocationTargetException if is not possible to invoke the method
   */
  public List<SNSUser> readAndUpload() throws ParseException, ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException,
      NoSuchMethodException, InvocationTargetException {
    SNSUserStore store = this.company.getSNSUserStore();

    List<String[]> userDataList = csvReader.read();

    List<SNSUser> userList = store.registerListOfUsers(userDataList);

    return userList;
  }
}
