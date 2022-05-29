package app.controller;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;
import app.domain.model.CSVReader;
import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.model.store.SNSUserStore;

/**
 * Vaccination Center mapper
 * 
 * @autor Carlos Lopes <1211277@isep.ipp.pt>
 */
public class UploadUsersFromFileController {

  private Company company;
  private CSVReader csvReader;

  public UploadUsersFromFileController(Company company) {
    this.company = company;
    this.csvReader = null;
  }

  public CSVReader createCsvReader(String filePathName) {
    this.csvReader = new CSVReader(filePathName);
    return csvReader;
  }

  public List<SNSUser> readAndUpload() throws ParseException, ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {

    SNSUserStore store = this.company.getSNSUserStore();

    List<String[]> userDataList = csvReader.readSNSUserData();

    List<SNSUser> userList = store.registerListOfUsers(userDataList);

    return userList;
  }

}
