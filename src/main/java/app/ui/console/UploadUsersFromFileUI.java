package app.ui.console;

import java.text.ParseException;
import java.util.List;
import app.controller.App;
import app.controller.UploadUsersFromFileController;
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

    List<SNSUserRegisterInfoDTO> userRegisterInfoList = null;
    try {
      userRegisterInfoList = ctrl.readAndUpload();
    } catch (ParseException e) {
      e.printStackTrace();
    }

    if (userRegisterInfoList == null) {
      System.out.println("No users registered!");
    } else {
      displayRegisteredUsers(userRegisterInfoList);
    }
  }

  private void displayRegisteredUsers(List<SNSUserRegisterInfoDTO> userRegisterInfoList) {
    Utils.showList(userRegisterInfoList, "\nUser Successfully registered:");
  }

  public void insertData() {
    String filePath = Utils.readLineFromConsole("File Path: ");

    ctrl.createCsvReader(filePath);
  }

}

