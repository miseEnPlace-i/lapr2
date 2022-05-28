package app.ui.console;

import java.text.ParseException;
import java.util.List;
import app.controller.App;
import app.controller.UploadUsersFromFileController;
import app.domain.model.SNSUser;
import app.dto.SNSUserRegisterInfoDTO;
import app.ui.console.utils.Utils;

/**
 * Vaccination Center mapper
 * 
 * @autor Carlos Lopes <1211277@isep.ipp.pt>
 */
public class UploadUsersFromFileUI implements Runnable {

  private UploadUsersFromFileController ctrl;

  public UploadUsersFromFileUI() {
    ctrl = new UploadUsersFromFileController(App.getInstance().getCompany());
  }

  @Override
  public void run() {
    System.out.println("\nRegister Vaccine UI:");

    insertData(); // asks to insert file path and instantiates CSVReader

    List<SNSUser> userList = null;
  
      try {
        userList = ctrl.readAndUpload();
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    

    if (userList == null) {
      System.out.println("No users registered!");
    } else {
      displayRegisteredUsers(userList);
    }
  }

  private void displayRegisteredUsers(List<SNSUser> userList) {
    Utils.showList(userList, "\nRegistered Users:\n");
  }

  public void insertData() {
    String filePath = Utils.readLineFromConsole("File Path: ");

    ctrl.createCsvReader(filePath);
  }

}

